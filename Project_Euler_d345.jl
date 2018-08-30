#Returns the index of the max value from an array
function getMax(arr)
	maxValue = arr[1]
	maxIndex = 1
	for i = 2:length(arr)
		if arr[i] > maxValue 
			maxValue = arr[i]
			maxIndex = i
		end
	end
	return maxIndex
end

#Returns an array of elements 1:n in a random order.
function randomArray(n)
	x = collect(1:n)
	for i = 1:n
		index = rand(x)
		temp = x[index]
		x[index] = x[i]
		x[i] = temp
	end
	return x
end

#Function that returns the scaling factor for choosing the largest number.
#Will get the scaling factor for smaller elimation by doing 1 minus larger scaling factor.
function getScaling(n,start,stop)
	toAdd = (stop-start)/(n-1)
	scales = zeros(15)
	scales[1] = start
	scales[n] = stop
	for i = 2:n-1 scales[i] = scales[i-1]+toAdd end
	return scales
end

#Passing in the value of the matrix.
perm  =   [[7,53,183,439,863,497,383,563,79,973,287,63,343,169,583],
	[627,343,773,959,943,767,473,103,699,303,957,703,583,639,913],
	[447,283,463,29,23,487,463,993,119,883,327,493,423,159,743],
	[217,623,3,399,853,407,103,983,89,463,290,516,212,462,350],
	[960,376,682,962,300,780,486,502,912,800,250,346,172,812,350],
	[870,456,192,162,593,473,915,45,989,873,823,965,425,329,803],
	[973,965,905,919,133,673,665,235,509,613,673,815,165,992,326],
	[322,148,972,962,286,255,941,541,265,323,925,281,601,95,973],
	[445,721,11,525,473,65,511,164,138,672,18,428,154,448,848],
	[414,456,310,312,798,104,566,520,302,248,694,976,430,392,198],
	[184,829,373,181,631,101,969,613,840,740,778,458,284,760,390],
	[821,461,843,513,17,901,711,993,293,157,274,94,192,156,574],
	[34,124,4,878,450,476,712,914,838,669,875,299,823,329,699],
	[815,559,813,459,522,788,168,586,966,232,308,833,251,631,107],
	[813,883,451,509,615,77,281,613,459,205,380,274,302,35,805]]

#=Begin the algorithm to find the Matrix Sum.
#Greedy algorithm that will run many times with different random condition to try and generate the optimal result.
#The local optimal solution is governed by 2 factors: Choosing the largest value in the column,
#But also choosing a row which eliminates smaller values in the future. (A way to help ensure larger values will be present for choice in future).
#The scaling for the contribution of each factor towards the total score of each choice will be weighted differently every iteration.
#Eliminating smaller numbers in the future will have less of an impact as we iterate columns,
#because there will be "less of a future to prime for".
#Every time we run this algorithm, the order we iterate the columns will be random.=#
solution = 0
n = 15
candidates = zeros(n)

#Run the algorithm many times
for i = 1:2500
	mat = deepcopy(perm)
	order = randomArray(n)
	largeScale = getScaling(n,1/3,2/3)
	total = 0

	#Iterate the columns
	for ii = 1:n
		col = order[ii]
		#Find the max values first
		maxLarge = mat[1][col]
		maxSmaller = 1e6

		#Iterate all the elements in this row, to compute max's and grab values.
		for row = 1:n
			if mat[row][col] == 0
				candidates[row] = 0
			else
				candidates[row] = 1
				#Compare for maxLarge
				if mat[row][col] > maxLarge maxLarge = mat[row][col] end

				#Iterate values in row to get a total to compare for maxSmaller
				currentSmall = mat[row][col]*-1
				for j = 1:n currentSmall += mat[row][j] end
				if currentSmall < maxSmaller maxSmaller = currentSmall end

				#Initialize the candidate array with starting values.
				candidates[row] = (1-largeScale[row])/currentSmall
			end
		end

		#Populate candidate matrix with scores
		for j = 1:n
			candidates[j] *= maxSmaller
			candidates[j] +=  mat[j][col]*largeScale[j]/maxLarge
		end

		#Find the index of the max score, and zero out all the elements in that column and row, and add this value to total
		index = getMax(candidates)
		value = mat[index][col]
		total += value
		for j = 1:n
			mat[j][col] = 0
			mat[index][j] = 0
		end
	end

	#If we beat our current best, update and print the change!
	if total > solution 
		println("Went from $solution to $total")
		global solution = total
	end
end
println("The solution is: $solution")

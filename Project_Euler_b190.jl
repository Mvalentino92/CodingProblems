#Calculates the value of the array
function getProduct(arr)
	sum = 1
	n = length(arr)
	for i = 1:n
		sum *= arr[i]^i
	end
	return sum
end

#Simulated annealing 
function annealing(m)
	#Initial array
	vals = ones(m)
	for i = 1:m-1
		give = vals[i]/rand(m:m*2+1)
		vals[i] -= (m-i)*give
		for j = i+1:m
			vals[j] += give
		end
	end

	#Initial solution, temperatue, and probablity function
	current = getProduct(vals)
	t = 1000
	dt = 0.01
	p(dx,t) = 1/exp(dx/t)

	#Peform annealing steps while temperature still "hot"
	while t > 0
		#Pick a random index, and have the value at this index,
		#give a random portion of it's value to every other index
		target = rand(1:m)
		give = vals[target]/rand(m:m*2+1)
		newVals = deepcopy(vals)
		newVals[target] -= give*(m-1)
		for i = 1:m
			if i != target
				newVals[i] += give
			end
		end

		#Sort the newValues, since you always want it in increasing order for processing
		sort!(newVals)
		neighbor = getProduct(newVals)

		#If better, keep this. If not, check probablity and possibly keep anyway
		if neighbor > current
			current = neighbor
			vals = newVals
		else
			if p(current-neighbor,t) > 0.5
				current = neighbor
				vals = newVals
			end
		end
		t -= dt
	end
	return trunc(current)
end

#Perform simulated annealing 50 times, and return the max value produced
function getAnswer()
	max = 0
	for i = 1:50
		current = 0
		for j = 2:15
			current += annealing(j)
		end
		max = current > max ? current : max
	end
	println(max)
end

getAnswer()

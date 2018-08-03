#Converts digits to a number
function digitsToNumber(digits)
	retval = BigInt(0)
	for i = 1:length(digits)
		retval = retval*10 + digits[i]
	end
	return retval
end

#Swapps out 0's for 7's.
function luckySevens(digits)
	for i = 1:length(digits)
		if digits[i] == 0
			digits[i] = 7
		end
	end
	return digits
end

#=Generate all the cubes from 1-10000. Break into digits. Sort the digits. Replace 0's with 7's.
Doing this after sorting will ensure that no accidental duplicates are obtained.
Convert digits back to numbers. Sort this array by these numbers (other part of tuple is holding the actual number it was. =#
cubes = []
for i = 1:10000
	push!(cubes,(digitsToNumber(luckySevens(sort(digits(BigInt(i)^3)))),i))
end
sort!(cubes)
solutions = []

#=Iterate the sorted list, and try to find 5 matches in a row.
If this is found, add all the actual numbers (other part of tuple), into an array and grab the minimum.
Cube this value, and add it to another array. Since we padded 7's, the first match will not be the lowest value.
Finally, find the min in this last array (holding all the solutions we found), because we are interested in the first (min) solution.=#
for i = 1:length(cubes)-6
	count = 0
	for j = i+1:i+4
		if cubes[i][1] == cubes[j][1]
			count += 1
		else
			break
		end
	end
	if count == 4
		sol = []
		for j = i:i+4
			push!(sol,cubes[j][2])
		end
		push!(solutions,minimum(sol)^3)
	end
end
println(minimum(solutions))

function getCF(D)
	CF = []
	#Get the number I need to match to in the numerator, if the denominator is 1.
	killMatch = trunc(sqrt(D))

	#Add this number as the first element in the continued fraction.
	push!(CF,BigInt(killMatch))

	#Set the initial numerator and denominator.
	numerator = killMatch
	denominator = 1
	count = 0;

	while (numerator != killMatch || denominator != 1) || count == 0
		#Fix the numerator
		if count > 0
			numerator = -1*(numerator - CF[end]*denominator)
		end

		#Keep old value
		oldNumerator = numerator

		#Update new values
		numerator = denominator
		denominator = D - oldNumerator*oldNumerator

		#Update new values again
		denominator = div(denominator,numerator)
		numerator = oldNumerator

		#Add the new term
		toAdd = div((killMatch + numerator),denominator)
		push!(CF,BigInt(toAdd))
		count += 1
	end
	return CF
end

function solvePell(D)
	#Get fractions
	CF = getCF(D)

	#Adjust if the length is even
	if length(CF) % 2 == 0
		for i = 2:length(CF)
			push!(CF,CF[i])
		end
	end

	val = CF[end-1]
	for i = length(CF)-1:-1:2
		val = CF[i-1]//1 + 1//val
	end
	return numerator(val)
end

function isPS(D)
	#Checks if number is perfect square
	sqrtD = trunc(sqrt(D))
	if sqrtD*sqrtD == D
		return true
	end
	return false
end

#Solves the problem
max = 0
dSol = 0
for D = 1:1000
	if isPS(D)
		continue
	end
	current = solvePell(D)
	if current > max
		max = current
		dSol = D
	end
end
println(dSol)

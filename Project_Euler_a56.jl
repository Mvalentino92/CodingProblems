maxVal = 0
for a = 1:99
	num = a
	for b = 1:99
		num *= BigInt(a)
		potentialMax = sum(digits(num))
		maxVal = potentialMax > maxVal ? potentialMax : maxVal
	end
end
println(maxVal)

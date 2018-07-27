sum = BigInt(0)
for i = 1:100
	sum += parse(BigInt,readline())
end
dig = reverse(digits(sum))
for i = 1:10
	print("$(dig[i]) ")
end
println()

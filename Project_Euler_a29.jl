arr = zeros(99*99)
index = 1
for a = BigInt(2):BigInt(100)
	for b = BigInt(2):BigInt(100)
		arr[index] = a^b
		index += 1
	end
end
println("Solution: $(length(unique(arr)))")

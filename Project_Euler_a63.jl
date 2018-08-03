count = 0
for i = 1:9
	power = 1
	digs = length(digits(BigInt(i)^power))
	while(digs == power)
		count += 1
		power += 1
		digs = length(digits(BigInt(i)^power))
	end
end
println(count)


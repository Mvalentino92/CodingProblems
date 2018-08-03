function partitions(n,summand,sum)
	if sum == n
		return 1
	end
	if summand == 0 || sum > n
		return 0
	end
	return partitions(n,summand,sum + summand) + partitions(n,summand-1,sum)
end
println("There are $(partitions(100,100,0)-1) integer partions for 100.")

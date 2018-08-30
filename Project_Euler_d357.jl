#Do a sieve and save the prime array and a second candidate 
#with all numbers 1 less than every prime.
bound = 100000000
primes = zeros(bound)
for i = 1:bound primes[i] = i end
primes[1] = -1

for i = 2:floor(Int64,sqrt(bound))
	if primes[i] != -1
		for j = i+i:i:bound primes[j] = -1 end
	end
end
candidates = [1]
for i = 3:2:bound
	if primes[i] != -1 push!(candidates,i-1) end
end

#Begin to iterate all the candidate numbers.
#Check the divisors up to the sqrt root, and if divisible
#check to see if value of d + n/d is in the prime array
s = 0
for i = 1:length(candidates)
	n = candidates[i]
	add = true
	for d = 1:floor(Int64,sqrt(n))
		if n % d == 0
			if primes[d + div(n,d)] == -1
				add = false
				break
			end
		end
	end
	if add global s += n end
end
println("The solution is: $s")

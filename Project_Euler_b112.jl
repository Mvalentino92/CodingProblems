function isBouncy(n)
	#Get the first digit
	current = n % 10
	n = div(n,10)

	#Initialize decreasing and increasing counts.
	increasing = 0
	decreasing = 0

	while n > 0
		next = n % 10
		n = div(n,10)

		#check if the digits are increasing or decreasing
		if next < current increasing += 1
		elseif next > current decreasing += 1 end
		current = next

		#if it both increased and decreased at some point, its bouncy.
		if increasing > 0 && decreasing > 0 return true end
	end
	return false
end

#Solves the problem
target = 0.99
tol = 1e-9
bouncyCount = 0
i = 1
while true
	if isBouncy(i) global bouncyCount += 1 end
	if abs(bouncyCount/i - target) < tol 
		println("The solution is: $i")
		break
	end
	global i += 1
end

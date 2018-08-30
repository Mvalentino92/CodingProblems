#Checks to see if current number is a match
#Will only recieve numbers already determined to be candidates.
#Proper length, and multiples of 10
function isMatch(n)
	n = div(n,100)
	for i = 9:-1:1
		if n % 10 != i return false end
		n  = div(n,100)
	end
	return true
end

#Lower and upper bounds for the blanks.
lower = 1020304050607080900
upper = 1929394959697989990

#Find where you must start and end with respect to square bounds
start = Int64(floor(sqrt(lower)))
stop = Int64(floor(sqrt(upper)))

#Have to start with a multiple of 10, in order to increment
#with multiples of 10
while start % 10 != 0 start += 1 end

#Increment multiples of 10 and check for matches
for i = start:10:stop
	if isMatch(i*i)
		println("The solution is: $i")
		break
	end
end

#Question: What is the index of the first term in the Fibonacci sequence to contain 1000 digits?

a = BigInt(1)  #Going to have to use BigInt
b = BigInt(1)
Total = 2		#Im already starting at the second index, so Total = 2
function Fib(a,b)
	hold = b
	b = a + b	#Just generating the next fib number with the two supplied
	a = hold
	return a,b
end

while length(digits(b)) < 1e3		#Keep getting them until you hit the first one with a length over 1000
	a,b = Fib(a,b)			#Keep updating a and b with each call of the function
	Total += 1			#Track which fib number were at!
end



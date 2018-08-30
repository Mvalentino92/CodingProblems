#A function that will return either true if the first number is larger, 
#and false if it is equal to or less than the second number.
#Breaks down the exponents and bases until it is clear which number is larger.
function isLarger(b1,e1,b2,e2)
	while true
		if b1 > b2
			if e2 > e1
				b1 /= b2
				e2 -= e1
			else return true end
		else
			if e1 > e2 
				b2 /= b1
				e1 -= e2
			else return false end
		end
	end
end

#Opening the file and reading in the values
f = open("p099_base_exp.txt")
f = readlines(f)
arr = zeros(length(f),2)

for i = 1:length(f)
	temp = split(f[i]," ")
	arr[i,1] = parse(Int64,temp[1])
	arr[i,2] = parse(Int64,temp[2])
end

#Solving the problem by doing a simple "Find Max" algorithm
maxLine = 1
for i = 2:length(f)
	if isLarger(arr[i,1],arr[i,2],arr[maxLine,1],arr[maxLine,2])
		global maxLine = i
	end
end
println("The solution is: $maxLine")

#Finds the triangle number
function tri(n)
	n = BigInt(n)
	return (n^2 + n)/2
end

#"Half" triangle numbers
function halfTri(start,levels)
	total = 0
	for i = start:-1:start - levels + 1
		total += i
	end
	return total
end

#Prints the fractal of divisible 7's
function printPascal(n)
	x = [BigInt(4),BigInt(6),BigInt(4)]
	for i = 6:n
		y = [x[1] + 1]
		for j = 1:length(x)-1
			push!(y,x[j] + x[j+1])
		end
		push!(y,x[end]+1)
		x = y
		print("$i: o ")
		for k = 1:length(x)
			if x[k] % 7 == 0
				print("* ")
			else print("o ")
			end
		end
		print("o ")
		println()
	end
end

#Finds the solution iteratively
function getPascal(n)
	count  = 0
	x = [BigInt(4),BigInt(6),BigInt(4)]
	for i = 6:n
		y = [x[1] + 1]
		for j = 1:length(x)-1
			push!(y,x[j] + x[j+1])
		end
		push!(y,x[end]+1)
		x = y
		for k = 1:length(x)
			if x[k] % 7 == 0
				count += 1
			end
		end
	end
	return tri(n) - count
end

#Finds the answer using fractals
function fractal(n)
	#Find the highest power that fufills complete fractal levels
	power = 0
	while 7^power - 1 < n
		power += 1
	end
	if(7^power - 1 == n) maxPower = power - 1
	else maxPower = power - 2 end

	#=White: top of triangle (the one we have to recur down)
	Black: The dense purely divisible sections.
	Total: The total of summing the white and black sections of this power. Completely filled out.=#
	data = zeros(BigInt,maxPower+2,3)
	for i = 2:maxPower+2
		white = data[i-1,3]
		black = tri(7^(i-1) - 1)
		if i != maxPower + 2 total = tri(7)*white + tri(7-1)*black 
		else total = 0 end
		data[i,1] = white
		data[i,2] = black
		data[i,3] = total
	end

	#Calls the recursive function
	toMinus = recurTriangle(n,data,maxPower,div(length(data),3))
	return tri(n) - toMinus
end

#The recursive function
function recurTriangle(n,data,maxPower,index)
	temp = BigInt(0)
	if maxPower == -1 return 0 end
	currentPower = 7^(maxPower+1)
	fullTriangles = div(n,currentPower)

	#Adding divisibles
	temp += halfTri(currentPower - 1,n % currentPower)*fullTriangles
	temp += tri(fullTriangles)*data[index,1] + tri(fullTriangles-1)*data[index,2]

	#The recursive call to add more divisibles
	temp += (fullTriangles+1)*recurTriangle(n % currentPower,data,maxPower-1,index-1)
	return temp
end

#Prints the percent of divisibles going to n = 10 billion. See how it approaches 100 percent.
n = 1
for i = 1:11
	println("$n rows: $(100 - (fractal(n)/tri(n))*100)")
	global n *= 10
end

println()
print("The answer is: $(fractal(1000000000))")

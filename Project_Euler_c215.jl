#Returns if layers are crack free
function crackFree(layerA,layerB)
	for i = 2:length(layerA)-1
		if layerA[i] + layerB[i] == 2
			return false
		end
	end
	return true
end

#Returns a list of all acceptable indices for each layer

function compatible(n::Int64)
	layers = bricks(n)
	pairs = Array{Array{Int64,1},1}(undef,length(layers))
	for i = 1:length(pairs) pairs[i] = [] end
	for i = 1:length(layers)-1
		for j = i+1:length(layers)
			if crackFree(layers[i],layers[j])
				append!(pairs[i],j)
				append!(pairs[j],i)
			end
		end
	end
	return pairs
end


#Calculates the solution (helper)
function getSolHelper(current::Int64,layers::Int64,pairs,table)
	#Base cases
	if table[current,layers] > 0 return table[current,layers] end
	if layers == 1 return length(pairs[current]) end

	#Add up all possibilities, updating table as we go
	count = 0
	for i in pairs[current]
		table[i,layers-1] = getSolHelper(i,layers-1,pairs,table)
		count += table[i,layers-1]
	end

	#Update table for current and return
	table[current,layers] = count
	return count
end

#Gets solution
function getSol(n::Int64,layers::Int64)
	pairs = compatible(n)
	table = zeros(length(pairs),10)
	count = 0
	for i = 1:length(pairs)
		count += getSolHelper(i,layers-1,pairs,table)
	end
	return count
end

#Returns all possibilities for playing bricks
function bricks(n::Int64)
	#Base cases
	if n < 2 return [[]] end
	if n == 2 return [[0,1]] end
	if n == 3 return [[0,0,1]] end
	if n == 4 return [[0,1,0,1]] end
	if n == 5 return [[0,1,0,0,1],[0,0,1,0,1]] end

	#Get solution for (n-2) and (n-3)
	n2 = bricks(n-2)
	n3 = bricks(n-3)

	#Append 2 and 3 onto future solutions respectively
	map(x -> append!(x,[0,1]),n2)
	map(x -> append!(x,[0,0,1]),n3)

	#Append those lists, and return
	return append!(n2,n3)
end

println(getSol(32,10))

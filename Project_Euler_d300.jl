function permutations(n::Int64)
	retval = zeros(Int64,2^n,n)
	col = 1
	while col <= n
		stride = 2^(n - col)
		val = 1;
		s = 1
		for i = 1:2^col
			for j = s:s+stride-1
				retval[j,col] = val
			end
			val *= -1;
			s += stride
		end
		col += 1
	end
	return retval
end

function getOptimal(arr::Array{Int64,1},directions::Array{Tuple{Int64,Int64},1})
	#This wont work for the 15 one, need to use ceil or something
	#Get the matrix primed, and pass to backtracking algorithm
	mat = zeros(Int64,2*length(arr)+1,2*length(arr)+1)
	mid = div(size(mat)[1],2) + 1	
	mat[mid,mid] = arr[1]
	mat[mid,mid+1] = arr[2]
	extra = arr[1] == 1 && arr[2] == 1 ? 1 : 0
	return extra + backTrack(mat,arr,3,directions,mid,mid+1)
end

function backTrack(mat::Array{Int64,2},arr::Array{Int64,1},
                   index::Int64,directions::Array{Tuple{Int64,Int64},1},
                   row::Int64,col::Int64)
	#Base case, if we exhausted the string, return 0 (can't add more h's)
	if index > length(arr) return 0 end

	#Initialize the max to be returned
	mx = -Inf

	#Iterate all directions
	for d in directions
		if mat[row+d[1],col+d[2]] != 0 continue end #occupied, continue
		
		#Otherwise place it there.
		mat[row+d[1],col+d[2]] = arr[index]

		#If we have a 1, check all the directions for new connections
		bonds = 0
		if arr[index] == 1
			bonds += mat[row+d[1]+1,col+d[2]] == 1 ? 1 : 0
			bonds += mat[row+d[1]-1,col+d[2]] == 1 ? 1 : 0
			bonds += mat[row+d[1],col+d[2]+1] == 1 ? 1 : 0
			bonds += mat[row+d[1],col+d[2]-1] == 1 ? 1 : 0
		end

		futureBonds = backTrack(mat,arr,index+1,directions,row+d[1],col+d[2])
		mx = bonds + futureBonds > mx ? bonds + futureBonds : mx
		mat[row+d[1],col+d[2]] = 0
	end
	return mx
end

function toBinary(arr::Array{Int64,1})
	retval = 0
	n = length(arr)
	for i = 1:length(arr)
		retval += arr[i] == -1 ? 2^(n-i) : 0
	end
	return retval
end
			
function solve(n::Int64)
	perm = permutations(n)
	doThem = zeros(Int64,2^n)
	directions = [(1,0),(0,1),(-1,0),(0,-1)]
	avg = getOptimal(perm[1,:],directions) + getOptimal(perm[end,:],directions)
	for i = 2:2^n-1
		if doThem[i] == -1 continue end
		arr = perm[i,:]
		bin = toBinary(reverse(arr))
		doThem[bin+1] = -1
		doThem[i] = -1
		toAdd = getOptimal(arr,directions)
		avg += bin + 1 == i ? toAdd : 2*toAdd
	end
	println(avg)
	return avg/2^n
end

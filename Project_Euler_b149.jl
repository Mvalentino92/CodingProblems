#O(n) algorithm to find the max sub array. Fastest solution
#s = start, k = stride, t = stop
function linear(arr::Array{Int64,1},s::Int64,k::Int64,t::Int64)
	max = -Inf
	jMax = -Inf
	for i = s:k:t
		jMax = jMax + arr[i] > arr[i] ? jMax + arr[i] : arr[i]
		max = jMax > max ? jMax : max
	end
	return max
end

function genFib(n::Int64)
	retval = zeros(Int64,n^2)
	for k = 1:55
		retval[k] = (100003 - 200003k + 300007k^3) % 1000000 - 500000
	end

	for k = 56:n^2
		retval[k] = (retval[k-24] + retval[k-55] + 1000000) % 1000000 - 500000
	end
	return retval
end

function solve()
	n = 2000
	arr = genFib(n)
	mx = -Inf

	#Rows and columns
	sR = 1 
	tR = n
	sC = 1
	tC = n^2 - n + 1
	sMaj = 1
	tMaj = n^2
	sMin = 1
	tMin = 1
	sMaj2 = 1 
	tMaj2 = n^2
	sMin2 = n
	tMin2 = n^2 - n + 1
	for i = 1:n
		current = linear(arr,sR,1,tR)
		current = max(current,linear(arr,sC,n,tC))
		current = max(current,linear(arr,sMaj,n+1,tMaj))
		current = max(current,linear(arr,sMin,n-1,tMin))
		current = max(current,linear(arr,sMaj2,n+1,tMaj2))
		current = max(current,linear(arr,sMin2,n-1,tMin2))
		sR += 7
		tR += 7
		sC += 1
		tC += 1
		sMaj += 1
		tMaj -= n
		sMin += 1
		tMin += n
		sMaj2 += n
		tMaj2 -= 1
		sMin2 += n
		tMin2 -= 1
		mx = current > mx ? current : mx
	end

	return mx
end
	
	


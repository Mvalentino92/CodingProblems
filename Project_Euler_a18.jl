TriangleWork = [[95,64],[17,47,82],[18,35,87,10],[20,4,82,47,65],[19,1,23,75,3,34],[88,2,77,73,7,63,67],[99,65,4,28,6,16,70,92],[41,41,26,56,83,40,80,70,33],[41,48,72,33,47,32,37,16,94,29],[53,71,44,65,25,43,91,52,97,51,14],[70,11,33,28,77,73,17,78,39,68,17,57],[91,71,52,38,17,14,91,43,58,50,27,29,48],[63,66,4,68,89,53,67,30,73,16,69,87,40,31],[4,62,98,27,23,9,70,98,73,93,38,53,60,4,23]]
#TriangleWork= [[6,1],[8,11,5],[7,16,2,4],[10,9,1,5,7],[16,4,1,3,18,9],[20,4,11,13,7,8,11]]
Total = 75
for i = 1:14
	#println("HEY")
	Right = 0
	Left = 0
	LeftTotal = 0
	RightTotal = 0	
	LeftHold = 0
	RightHold = 0
	for j in TriangleWork
		RightSum = 0
		LeftSum = 0
		if size(j)[1] == 2
			LeftHold = j[1]
			RightHold = j[2]
			LeftTotal += j[1]
			RightTotal += j[2]
			if j[1] > j[2]
				Left += 1 
			elseif j[1] < j[2]
				Right += 1
			else
				Left += 0
				Right += 0
			end
		else
			Mid = Int(ceil(size(j)[1]/2))
			#println(Mid)
			LeftSum += sum(j[1:Mid])
			if size(j)[1] % 2 != 0
				RightSum += sum(j[Mid:end])
			else
				RightSum += sum(j[Mid+1:end])
			end
			LeftTotal += LeftSum
			RightTotal += RightSum
			#println("Left Sum is $LeftSum, Right Sum is $RightSum")
			if LeftSum > RightSum
				Left += 1
			elseif LeftSum < RightSum
				Right += 1 
			else
				Left += 0
				Right += 0
			end
		end
	end
	#println("Left is $Left, and Right is $Right")
	if Left > Right
		for k=1:size(TriangleWork)[1]
			#println(TriangleWork[k])
			deleteat!(TriangleWork[k],size(TriangleWork[k])[1])
			#println(TriangleWork[k])
		end
	elseif Left < Right
		for k=1:size(TriangleWork)[1]
			deleteat!(TriangleWork[k],1)
		end
	else
		#println("Left is $LeftTotal, right is $RightTotal")
		if LeftHold > RightHold
			for k=1:size(TriangleWork)[1]
				deleteat!(TriangleWork[k],size(TriangleWork[k])[1])
			end
		else
			for k=1:size(TriangleWork)[1]
				deleteat!(TriangleWork[k],1)
			end
		end
	end
	#println(TriangleWork)
	println(TriangleWork[1][1])
	Total += TriangleWork[1][1]
	TriangleWork = TriangleWork[2:end]
	#println(TriangleWork)
end

#Set up the arrays to hold the amount of times each number can be rolled
peters = zeros(Int64,4*9 - 8)
p = 4^9
colins = zeros(Int64,6*6 - 5)
c = 6^6

#Adding up peters counts
for i = 1:4
	for i2 = 1:4
		for i3 = 1:4
			for i4 = 1:4
				for i5 = 1:4
					for i6 = 1:4
						for i7 = 1:4
							for i8 = 1:4
								for i9 = 1:4
									val = i + i2 + i3 + i4 + i5 + i6 + i7 + i8 + i9
									peters[val-8] += 1
								end
							end
						end
					end
				end
			end
		end
	end
end

#Adding up colins counts
for j = 1:6
	for j2 = 1:6
		for j3 = 1:6
			for j4 = 1:6
				for j5 = 1:6
					for j6 = 1:6
						val = j + j2 + j3 + j4 + j5 + j6 
						colins[val - 5] += 1
					end
				end
			end
		end
	end
end

#Iterating through. Peters chances are the sum of the following.
#His chance to roll a certain number, multiplied by the chance that colin will roll a lesser number.
chanceToWin = 0
for i = 1:length(peters)
	hand = i + 8
	winHand = 0
	for j = 1:length(colins)
		opponentHand = j + 5
		if hand > opponentHand winHand += colins[j] end
	end
	winHand /= c
	global chanceToWin += (peters[i]/p)*winHand
end
println("Peter's chance to win is: $chanceToWin")

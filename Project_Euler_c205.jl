#Set up the arrays to hold the amount of times each number can be rolled
peters = zeros(Int64,4*9 - 8)
p = 4^9
colins = zeros(Int64,6*6 - 5)
c = 6^6

#Adding up peters counts
for i = 1:4
	for ii = 1:4
		for iii = 1:4
			for iiii = 1:4
				for iiiii = 1:4
					for iiiiii = 1:4
						for iiiiiii = 1:4
							for iiiiiiii = 1:4
								for iiiiiiiii = 1:4
									val = i + ii + iii + iiii + iiiii + iiiiii + iiiiiii + iiiiiiii + iiiiiiiii
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
	for jj = 1:6
		for jjj = 1:6
			for jjjj = 1:6
				for jjjjj = 1:6
					for jjjjjj = 1:6
						val = j + jj + jjj + jjjj + jjjjj + jjjjjj 
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

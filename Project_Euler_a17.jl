#Question: Counting the number of words

Single = ["","one","two","three","four","five","six","seven","eight","nine"]
Doubles = ["ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"]
Tens = ["twenty","thirty","fourty","fifty","sixty","seventy","eighty","ninety"]

Hundreds = ["onehundred","twohundred","threehundred","fourhundred","fivehundred","sixhundred","sevenhundred","eighthundred","ninehundred"]

total = 0

for i in Hundreds
	for j in Tens
		for k in Single
			total += sum([length(i),length(j),length(k),3])
			#total += 1
		end
	end
end

for i in Doubles
	push!(Single,i)
end
deleteat!(Single,1)

for i in Hundreds
	for j in Single
		total += sum([length(i),length(j),3])
		#total += 1
	end
end

Single = ["one","two","three","four","five","six","seven","eight","nine"] 
Doubles = ["ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"]

for i in Single
	total += length(i)
	#total += 1
end

for i in Doubles 
	total += length(i)
	#total += 1
end

for i in Hundreds
	total += length(i)
	#total += 1
end

push!(Single,"")

for i in Tens
	for j in Single
		total += sum([length(i),length(j)])
		#total += 1
	end
end

total += 11

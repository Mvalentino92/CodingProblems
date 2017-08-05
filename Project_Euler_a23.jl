x = open("p022_names.txt");     #reading the text file, and cleaning it up into
x = readline(x);		#a nice list
x = split(x,",");
Names = []
for i in x
	push!(Names,i)
end

New = []
for i in Names
	push!(New,strip(i,'"'))
end
FinalNames = sort(New)		#sorting the list alphabetically, and then
				#making the dictionary for the values
Letter_Values = Dict('A' => 1, 'B' => 2, 'C' => 3, 'D' => 4,'E' => 5,'F' => 6,
'G'=> 7,'H' => 8, 'I' => 9, 'J' => 10, 'K' => 11, 'L' => 12, 'M' => 13, 'N'=>
14, 'O' => 15, 'P' => 16, 'Q' => 17, 'R' => 18, 'S' => 19, 'T' =>20, 'U' => 21,
'V' => 22, 'W' => 23, 'X' => 24, 'Y' => 25, 'Z' => 26)

Total = 0

for i=1:length(FinalNames)
	Current_Name = FinalNames[i]
	NameScore = 0			#iterating through and finding Namescore
	for j=1:length(Current_Name)
		NameScore += Letter_Values[Current_Name[j]]
	end
	Total += NameScore*i
end

println("The Total is $Total")

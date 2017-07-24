#Question: What is the sum of th digits produced by 2^1000?


Number = 2^(BigInt(1000))

String = string(Number)

Total = 0

for i in String
	Total += parse(Int,i)
end

println("The sum of the digits is $Total")



#= Question: A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:

012   021   102   120   201   210

What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9? =#

Elements = [i for i=0:9]	#Creating the array of values for permuations

Solution = []
Number = 1e6			#Going to use a while loop to pick off each Number
Factorial = 9			#The position of the next number is given by dividing Number by the current factorial and rounding up

while length(Elements) > 0
	Slot = Number/factorial(Factorial)	#For instance, Slot will be 2.753424
	Remainder = Slot % 1			#I also need the Remainder, (The .753424) to caluclate the next value for Number
	if Remainder == 0			#If the Remainder happens to be 0 (which happens), just set it to 1
		Remainder = 1
	end
	Index = Int(ceil(Slot))			#So the index  for next digit in the final solution will be the Slot rounded up
	push!(Solution,Elements[Index])		#So whatever that index is, say 3, the next spot in the final solution will be the third element in Elements
	deleteat!(Elements,Index)		#Now we have to delete that element though because weve used it
	Number = round(Remainder*(factorial(Factorial)))	#For the next iteration, Number is going to be the Remainder, multiplied by the current factorial
	Factorial -= 1					#For the next iteration, since were sort of recursively figuring out each spot in the solution, were going to use the next factorial (in decreasing order)
end

println("The solution is $(join(Solution))")
	

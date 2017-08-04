#This converges of square roots as well.


#=N = 2
NPower = N^2
End = 1e6
NDecrease = NPower/End
Deci = 0
Deci2 = 0
for i=1:End
	Deci = N/(NPower + Deci) 
	Deci += NDecrease
	Deci2 = N/(NPower + Deci2)
	NPower -= NDecrease
end
Final = (Deci + Deci2)/2
println(Deci)=#


Deci = .0001
N = 16
End = 1e6
Sum = 0
NDecrease = N/End
for i=1:End
	Deci = 8/(Deci*(NDecrease+N))
	Sum += Deci	
	N -= NDecrease
end


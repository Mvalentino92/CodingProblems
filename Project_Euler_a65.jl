#=
m = multiplier
n = numerator
=#

m = 2
nOld = BigInt(2)
nNext = BigInt(3)

for i = 3:100
	temp = nOld
	nOld = nNext
	if i % 3 == 0
		nNext = nOld*m + temp
		m += 2
	else
		nNext = nOld + temp
	end
end
println(sum(digits(nNext)))

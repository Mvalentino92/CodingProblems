tri(n) = (n^2 + n)/2
diff = 10
thresh = 2e6
for i = 1:265
	for j = i:265
		if abs(tri(i)*tri(j) - thresh) < diff
			println(i*j)
		end
	end
end

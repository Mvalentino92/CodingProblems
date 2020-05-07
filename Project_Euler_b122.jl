using LightGraphs, MetaGraphs

function solve(n::Int)

	list = []
	total = 1

	# Create graph
	g = MetaDiGraph(SimpleGraph(n))

	# Add edges between vertices
	for i = 1:n
		for j = i+1:2*i
			add_edge!(g,i,j)
		end
	end

	# Add the trivial edge properties between 1 and 2
	set_props!(g,1,2,Dict(:val => 1, :path => Set([1,2])))

	# Begin to iterate and update
	for v = 3:n

		# Track minimum for v
		retval = Inf
		retpath = Set()

		# Get edges properties for every edge pointing here
		# u -> v
		for u in inneighbors(g,v)

			# Get difference for vertex that needs to be present
			diff = v - u

			# Set mn to Inf and mnpath to empty Set
			mn = Inf
			mnpath = Set()

			# Begin to iterate all the in neighbors of u
			# and find which path from w -> u has the least edge
			# weight while having diff present in it's path
			#println("u is: ",u," v is: ",v)
			for w in inneighbors(g,u)
				#println("w is: ",w," u is: ",u)

				# Get both values from edge
				val = get_prop(g,w,u,:val)
				path = get_prop(g,w,u,:path)

				# Check to see if diff is in path and val is better
				if diff in path && val < mn
					#println("val from ",w," to ",u," is: ",val," and mn is: ",mn)
					mn = val
					mnpath = copy(path)
				end
			end

			# Set the properties for u -> v
			mn += 1
			push!(mnpath,u)
			push!(mnpath,v)
			set_props!(g,u,v,Dict(:val => mn, :path => mnpath))

			# Update retval mn
			if mn < retval
				retval = mn
				retpath = mnpath
			end
		end

		# Print retval
		total += retval
	end
	println(total)
end

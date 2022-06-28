db.createUser(
	{
		user: "tipikae",
		pwd: "231045",
		roles: [
			{
				role: "readWrite",
				db: "mediscreen"
			}
		]
	}
)
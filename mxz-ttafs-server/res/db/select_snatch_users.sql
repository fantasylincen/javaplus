SELECT
	*
FROM
	(
		SELECT
			uname,
			RAND() AS random,
			sum(count) AS xx
		FROM
			TABLE_NAME
		WHERE
			(
				typeId = ?
				OR typeId = ?
				OR typeId = ?
				OR typeId = ?
				OR typeId = ?
			)
		AND uname <> ?
		GROUP BY
			uname
		ORDER BY
			random
	) AS users
WHERE
	users.xx >= 2
AND uname IN 
	(
		SELECT
			uname
		FROM
			TABLE_NAME
		WHERE
			typeid = ?
		AND count > 0
	)

LIMIT 4
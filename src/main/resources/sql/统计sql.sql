SELECT
	c2 AS companyId,
	round( IFNULL( n, 0 ) / s, 4 ) ratio
FROM
	(
SELECT
	*
FROM
	(
SELECT
	pt.company_id c1,
	count( 1 ) n
FROM
	admin.org_group og,
	admin.org_orgs oo,
	patrol_task pt
WHERE
	og.state = 1
	AND oo.PK_GROUP = og.PK_GROUP
	AND oo.ISBUSINESSUNIT = 'Y'
	AND oo.ENABLESTATE = 2
	AND oo.id = pt.company_id
	AND pt.state IN ( 5, 7 )
	AND pt.deleted = 0
	AND pt.plan_start_time BETWEEN #{startTime}

	AND #{endTime}

	AND EXISTS (
SELECT
*
FROM
admin.sys_role sr
WHERE
sr.company_id = oo.id
AND sr.state = 1
AND sr.type = 2
AND sr.deleted = 0
)
GROUP BY
	pt.company_id
	) t1
	RIGHT JOIN (
	SELECT
		pt.company_id c2,
		count( 1 ) s
	FROM
		admin.org_group og,
		admin.org_orgs oo,
		patrol_task pt
	WHERE
		og.state = 1
		AND oo.PK_GROUP = og.PK_GROUP
		AND oo.ISBUSINESSUNIT = 'Y'
		AND oo.ENABLESTATE = 2
		AND oo.id = pt.company_id
		AND pt.state IN ( 1, 2, 3, 4, 5, 7 )
		AND pt.deleted = 0
		AND pt.plan_start_time BETWEEN #{startTime}

		AND #{endTime}

		AND EXISTS (
		SELECT
			*
		FROM
			admin.sys_role sr
		WHERE
			sr.company_id = oo.id
			AND sr.state = 1
			AND sr.type = 2
			AND sr.deleted = 0
		)
	GROUP BY
		pt.company_id
	) t2 ON t1.c1 = t2.c2
) t;
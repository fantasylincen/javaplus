--游戏数据
--logs:out( "Enter [ gameData.lua ]" )

-------------------------------可用类-----------------------------------
--logs 日志输出类函数 logs:debug( "" ) 
------------------------------------------------------------------------


--	调整前排名方式				调整后排名方式		
--	名次	钻石	金币（w）	名次	钻石	金币（w）
--	1~3		600		80			1		600		80
--	4~10	450		60			2		500		60
--	11~30	300		40			3		350		50
--	31~50	200		20			4~10	200		30
--	50~n	50		15			11~50	100		10
--								50~n	25		5
-- 大龙排名奖励
local rankingAward = {
		--	名次		钻石			金币
		{ 	rank=1, 	crystal=600, 	gold=800000 	},
		{ 	rank=2, 	crystal=500, 	gold=600000 	},
		{ 	rank=3, 	crystal=350, 	gold=500000 	},
		{ 	rank=10, 	crystal=200, 	gold=300000 	},
		{ 	rank=50, 	crystal=100, 	gold=100000 	},
		{ 	rank=-1, 	crystal=25,  	gold=50000 		} 
}

-- 获得大龙排名奖励
--@param ranking 排名
--@return 返回2个值 一个水晶一个金币
function getDragonRankingAward( ranking )

	if ranking <= 0 then 
		return 0,0
	end
	
	for i = 1, #rankingAward do
		--logs:out( "名次=" .. rankingAward[i].rank .. ", 钻石=" .. rankingAward[i].crystal .. ", 金币=" .. rankingAward[i].gold )
		if ranking <= rankingAward[i].rank or rankingAward[i].rank == -1 then
			return rankingAward[i].crystal, rankingAward[i].gold 
		end
	end
	
	return rankingAward[6].crystal, rankingAward[6].gold
end


-- 排位排名奖励
local rankAward = {
	--		名次		金币			钻石
		{ rank=1, 		gold=1000000, 	crystal=600 },
		{ rank=2, 		gold=800000, 	crystal=500 },
		{ rank=3, 		gold=600000, 	crystal=400 },
		{ rank=10, 		gold=500000, 	crystal=300 },
		{ rank=50, 		gold=300000, 	crystal=200 },
		{ rank=100, 	gold=200000, 	crystal=150 },
		{ rank=300, 	gold=150000, 	crystal=100 },
		{ rank=500, 	gold=100000, 	crystal=50 	},
		{ rank=1000, 	gold=50000, 	crystal=10 	},
		{ rank=-1, 		gold=10000, 	crystal=0	}
}
-- 获得排位排名奖励
--@param rank 排名
--@return 2个返回值  金币,水晶
function getQualifyingRankingAward( rank )

	if rank <= 0 then 
		return 0,0
	end
	
	for i = 1, #rankAward do
		--logs:out( "名次=" .. rankAward[i].rank .. ", 钻石=" .. rankAward[i].crystal .. ", 金币=" .. rankAward[i].gold )
		if rank <= rankAward[i].rank or rankAward[i].rank == -1 then
			return rankAward[i].gold, rankAward[i].crystal 
		end
	end
	
	return rankAward[10].gold, rankAward[10].crystal
end


--队长技能消耗基数
local consumeCardinality = 20 

-- 获得刷新队长技能需要消耗的水晶
--@param useTimes 使用次数
--@return 返回1个值 消耗水晶数
function getUpdateCaptainSkillsNeedCrystal( useTimes )

	local ret = consumeCardinality * ( useTimes - 1 )
	if ret > 100 then
		ret = 100
	end
	--logs:out( "lua中调用 刷新队长技能消耗水晶=" .. ret )
	return ret;
end

-- 获得大龙复活需要消耗的水晶
--@param resurgenceTimes 复活次数
--@return 返回1个值 消耗水晶数
function getDragonResurgenceNeedCrystal( resurgenceTimes )
	return 20 * (resurgenceTimes + 1);
end
-- 获得大龙复活需要消耗的金币
--@param resurgenceTimes 复活次数
--@return 返回1个值 消耗水晶数
function getDragonResurgenceNeedCash( resurgenceTimes )
	return 20000 * (resurgenceTimes + 1);
end


-------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------下面为 购买各种所需水晶操作---------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------
--[ 获得各种购买所需要水晶 ]
--@param 体力,好友上限,背包上限,装备背包上限,pvp次数*,rank次数*,金币&
--@return 对应参数
function getAllBuyNeedCrystal( args1, args2, args3, args4, args5, args6, args7 )
	local ret1 = getBuyNeedCrystal_1( args1 , 0, 0 );
	local ret2 = getBuyNeedCrystal_2( args2 , 0 );
	local ret3 = getBuyNeedCrystal_3( args3 , 0 );
	local ret4 = getBuyNeedCrystal_4( args4 , 0 );
	local ret5 = getBuyNeedCrystal_5( args5 );
	local ret6 = getBuyNeedCrystal_6( args6 );
	local ret7 = getBuyNeedCrystal_7( args7 , 1 );
	return ret1,ret2,ret3,ret4,ret5,ret6,ret7
end
--[ 体力 ]
--@param 当前次数,当前体力值
--@return 当前消耗水晶,下次消耗水晶,增加的体力值
function getBuyNeedCrystal_1( times, currentValue, maxValue )
	return needCrystal_1( times ), needCrystal_1( times + 1 ), (maxValue - currentValue);
end
function needCrystal_1( times )
	if times <= 1 then
		return 50
	elseif times <= 2 then
		return 80
	else
		return 100
	end
end
--[ 好友上限 ]
--@param 当前次数,当前好友上限
--@return 当前消耗水晶,下次消耗水晶,增加的好友上限
function getBuyNeedCrystal_2( times, currentValue )
	return needCrystal_2( times ), needCrystal_2( times + 1 ), 5
end
function needCrystal_2( times )
	local needCrystal = times * 10 + 30;
	if needCrystal > 100 then
		needCrystal = 100;
	end
	return needCrystal;
end
--[ 背包上限 ]
--@param 当前次数,当前背包上限
--@return 当前消耗水晶,下次消耗水晶,增加的背包上限
function getBuyNeedCrystal_3( times, currentValue )
	return needCrystal_3( times ), needCrystal_3( times + 1 ), 5
end
function needCrystal_3( times )
	local needCrystal = times * 10 + 30;
	if needCrystal > 100 then
		needCrystal = 100;
	end
	return needCrystal;
end
--[ 装备背包上限 ]
--@param 当前次数,当前装备背包上限
--@return 当前消耗水晶,下次消耗水晶,增加的装备背包上限
function getBuyNeedCrystal_4( times, currentValue )
	return needCrystal_4( times ), needCrystal_4( times + 1 ), 5
end
function needCrystal_4( times )
	local needCrystal = times *10 + 30;
	if needCrystal > 100 then
		needCrystal = 100;
	end
	return needCrystal;
end
--[ pvp次数 ]
--@param 当前次数
--@return 当前消耗水晶,下次消耗水晶
function getBuyNeedCrystal_5( times )
	return needCrystal_5( times ), needCrystal_5( times + 1 )
end
function needCrystal_5( times )
	local needCrystal = times * 10 +20;
	if needCrystal > 100 then
		needCrystal = 100;
	end
	return needCrystal;
end
--[ rank次数 ]
--@param 当前次数
--@return 当前消耗水晶,下次消耗水晶
function getBuyNeedCrystal_6( times )
return needCrystal_6( times ), needCrystal_6( times + 1 )
end
function needCrystal_6( times )
	local needCrystal = times * 10 + 20;
	if needCrystal > 100 then
		needCrystal = 100;
	end
	return needCrystal;
end
--[ 金币 这里只算一次的]
--@param 当前次数0开始,购买的次数
--@return 当前消耗水晶,下次消耗水晶,增加的金币
--[第一次水晶购买金币比列为 50水晶 = 10万金币]
function getBuyNeedCrystal_7( times )
	return needCrystal_7( times ), needCrystal_7( times + 1 ), 100000
end
function needCrystal_7( times )
	local needCrystal = times * 10 + 50;
	if needCrystal > 100 then
		needCrystal = 100;
	end
	return needCrystal;
end
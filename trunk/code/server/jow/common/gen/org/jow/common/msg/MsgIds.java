package org.jow.common.msg;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import org.jow.core.support.SysException;

public class MsgIds {
	public static final int CSLogin = 111;
	public static final int SCLoginResult = 112;
	public static final int CSAccountReconnect = 121;
	public static final int SCAccountReconnectResult = 122;
	public static final int SCAccountLoginQueue = 153;
	public static final int CSKeyActivate = 154;
	public static final int SCKeyActivate = 155;
	public static final int CSBattleStart = 1201;
	public static final int SCBattleStart = 1202;
	public static final int CSBattleDo = 1203;
	public static final int SCBattleDo = 1204;
	public static final int CSBattleOver = 1205;
	public static final int SCBattleOver = 1206;
	public static final int SCTick = 1207;
	public static final int SCBattleStageSwitch = 1208;
	public static final int CSBattleStageSwitchEnd = 1209;
	public static final int CSBattleLeave = 1210;
	public static final int SCBattleLeaveResult = 1211;
	public static final int SCUpdataCard = 1301;
	public static final int CSCarryCard = 1306;
	public static final int SCCarryCard = 1307;
	public static final int CSTakeOffCard = 1308;
	public static final int SCTakeOffCard = 1309;
	public static final int CSCardLevelUp = 1310;
	public static final int SCCardLevelUp = 1311;
	public static final int SCChat = 1801;
	public static final int CSChat = 1802;
	public static final int SCChatPack = 1803;
	public static final int CSGetWorldChatBuffer = 1804;
	public static final int CSInitData = 1101;
	public static final int SCInitData = 1102;
	public static final int SCHumanInfoChange = 1103;
	public static final int SCServerConfig = 1104;
	public static final int CSPing = 1116;
	public static final int SCPing = 1117;
	public static final int SCHumanKick = 1120;
	public static final int SCHint = 1121;
	public static final int CSAddFriend = 1601;
	public static final int SCAddFriend = 1602;
	public static final int CSDeleteFriend = 1603;
	public static final int SCDeleteFriend = 1604;
	public static final int CSLaud = 1605;
	public static final int SCLaudResult = 1606;
	public static final int CSFindFriend = 1607;
	public static final int SCFindFriend = 1608;
	public static final int CSGetMyFriends = 1609;
	public static final int SCGetMyFriends = 1610;
	public static final int CSGetMyFans = 1611;
	public static final int SCGetMyFans = 1612;
	public static final int CSGetPlayerInfo = 1613;
	public static final int SCGetPlayerInfo = 1614;
	public static final int CSGetPlayerLaud = 1615;
	public static final int SCGetPlayerLaud = 1616;
	public static final int CSGetRecentPlayer = 1617;
	public static final int SCGetRecentPlayers = 1618;
	public static final int CSGetNearbyPlayer = 1619;
	public static final int SCGetNearbyPlayer = 1620;
	public static final int CSGetBlacklist = 1621;
	public static final int SCGetBlacklist = 1622;
	public static final int CSAddBlacklist = 1623;
	public static final int SCAddBlacklist = 1624;
	public static final int CSRemoveBlacklist = 1625;
	public static final int SCRemoveBlacklist = 1626;
	public static final int CSGetRecentChat = 1627;
	public static final int SCGetRecentChat = 1628;
	public static final int CSChangeName = 2001;
	public static final int SCChangeName = 2002;
	public static final int CSChangeHead = 2003;
	public static final int SCChangeHead = 2004;
	public static final int CSGetHistoryData = 2005;
	public static final int SCGetHistoryData = 2006;
	public static final int CSGetRoundData = 2007;
	public static final int SCGetRoundData = 2008;
	public static final int CSGetSeasonData = 2009;
	public static final int SCGetSeasonData = 2010;
	public static final int SCUpdataHutItem = 1701;
	public static final int CSOpenHut = 1702;
	public static final int SCOpenHut = 1703;
	public static final int CSUseHutItem = 1704;
	public static final int SCUseHutItem = 1705;
	public static final int CSBuyHutItem = 1706;
	public static final int SCBuyHutItem = 1707;
	public static final int CSGiftHutItem = 1708;
	public static final int SCGiftHutItem = 1709;
	public static final int SCInitMailList = 1901;
	public static final int CSGetMailDetail = 1902;
	public static final int SCGetMailDetail = 1903;
	public static final int CSReadMail = 1904;
	public static final int SCReadMail = 1905;
	public static final int SCMailNewRemind = 1906;
	public static final int CSPickupMailItem = 1907;
	public static final int SCPickupItemMailResult = 1908;
	public static final int CSMailRemove = 1909;
	public static final int SCMailRemove = 1910;
	public static final int CSRoomCreate = 1401;
	public static final int SCRoomCreateResult = 1402;
	public static final int CSRoomAddRobot = 1403;
	public static final int SCRoomAddRobot = 1404;
	public static final int CSRoomKick = 1405;
	public static final int SCRoomKick = 1406;
	public static final int CSRoomInvite = 1407;
	public static final int SCRoomInvite = 1408;
	public static final int CSRoomChangeIndex = 1409;
	public static final int SCRoomChangeIndex = 1410;
	public static final int CSRoomSwapIndexRequest = 1411;
	public static final int SCRoomSwapIndexRequest = 1412;
	public static final int CSRoomSwapIndexResponse = 1413;
	public static final int SCRoomSwapIndexResponse = 1414;
	public static final int CSTeamCreate = 1501;
	public static final int SCTeamCreateResult = 1502;
	public static final int CSTeamInvite = 1503;
	public static final int SCTeamInviteResult = 1504;
	public static final int SCTeamInviteReceive = 1505;
	public static final int CSTeamJoin = 1506;
	public static final int SCTeamJoinResult = 1507;
	public static final int SCTeamJoin = 1508;
	public static final int CSTeamLeave = 1509;
	public static final int SCTeamLeaveResult = 1510;
	public static final int SCTeamLeave = 1511;
	public static final int CSTeamKick = 1512;
	public static final int SCTeamKickResult = 1513;
	public static final int SCTeamKick = 1514;
	public static final int CSTeamPrepare = 1515;
	public static final int SCTeamPrepareResult = 1516;
	public static final int SCTeamPrepare = 1517;
	public static final int SCSyncMemberInfo = 1518;
	public static final int CSGmCmd = 9901;
	
	//消息CLASS与消息ID的对应关系<消息class, 消息ID>
	private static final Map<Class<? extends Message>, Integer> classToId = new HashMap<>();
	//消息ID与消息CLASS的对应关系<消息ID, 消息class>
	private static final Map<Integer, Class<? extends Message>> idToClass = new HashMap<>();
	
	static {
		//初始化消息CLASS与消息ID的对应关系
		initClassToId();
		//初始化消息ID与消息CLASS的对应关系
		initIdToClass();
	}
	
	/**
	 * 获取消息ID
	 * @param clazz
	 * @return
	 */
	public static int getIdByClass(Class<? extends Message> clazz) {
		return classToId.get(clazz);
	}
	
	/**
	 * 获取消息CLASS
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getClassById(int msgId) {
		return (T) idToClass.get(msgId);
	}
	
	/**
	 * 获取消息名称
	 * @param clazz
	 * @return
	 */
	public static String getNameById(int msgId) {
		try {
			return idToClass.get(msgId).getSimpleName();
		} catch (Exception e) {
			throw new SysException(e, "获取消息名称是发生错误：msgId={0}", msgId);
		}
	}
	
	/**
	 * 初始化消息CLASS与消息ID的对应关系
	 */
	private static void initClassToId() {
		classToId.put(MsgAccount.CSLogin.class, CSLogin);
		classToId.put(MsgAccount.SCLoginResult.class, SCLoginResult);
		classToId.put(MsgAccount.CSAccountReconnect.class, CSAccountReconnect);
		classToId.put(MsgAccount.SCAccountReconnectResult.class, SCAccountReconnectResult);
		classToId.put(MsgAccount.SCAccountLoginQueue.class, SCAccountLoginQueue);
		classToId.put(MsgAccount.CSKeyActivate.class, CSKeyActivate);
		classToId.put(MsgAccount.SCKeyActivate.class, SCKeyActivate);
		classToId.put(MsgBattle.CSBattleStart.class, CSBattleStart);
		classToId.put(MsgBattle.SCBattleStart.class, SCBattleStart);
		classToId.put(MsgBattle.CSBattleDo.class, CSBattleDo);
		classToId.put(MsgBattle.SCBattleDo.class, SCBattleDo);
		classToId.put(MsgBattle.CSBattleOver.class, CSBattleOver);
		classToId.put(MsgBattle.SCBattleOver.class, SCBattleOver);
		classToId.put(MsgBattle.SCTick.class, SCTick);
		classToId.put(MsgBattle.SCBattleStageSwitch.class, SCBattleStageSwitch);
		classToId.put(MsgBattle.CSBattleStageSwitchEnd.class, CSBattleStageSwitchEnd);
		classToId.put(MsgBattle.CSBattleLeave.class, CSBattleLeave);
		classToId.put(MsgBattle.SCBattleLeaveResult.class, SCBattleLeaveResult);
		classToId.put(MsgCard.SCUpdataCard.class, SCUpdataCard);
		classToId.put(MsgCard.CSCarryCard.class, CSCarryCard);
		classToId.put(MsgCard.SCCarryCard.class, SCCarryCard);
		classToId.put(MsgCard.CSTakeOffCard.class, CSTakeOffCard);
		classToId.put(MsgCard.SCTakeOffCard.class, SCTakeOffCard);
		classToId.put(MsgCard.CSCardLevelUp.class, CSCardLevelUp);
		classToId.put(MsgCard.SCCardLevelUp.class, SCCardLevelUp);
		classToId.put(MsgChat.SCChat.class, SCChat);
		classToId.put(MsgChat.CSChat.class, CSChat);
		classToId.put(MsgChat.SCChatPack.class, SCChatPack);
		classToId.put(MsgChat.CSGetWorldChatBuffer.class, CSGetWorldChatBuffer);
		classToId.put(MsgCommon.CSInitData.class, CSInitData);
		classToId.put(MsgCommon.SCInitData.class, SCInitData);
		classToId.put(MsgCommon.SCHumanInfoChange.class, SCHumanInfoChange);
		classToId.put(MsgCommon.SCServerConfig.class, SCServerConfig);
		classToId.put(MsgCommon.CSPing.class, CSPing);
		classToId.put(MsgCommon.SCPing.class, SCPing);
		classToId.put(MsgCommon.SCHumanKick.class, SCHumanKick);
		classToId.put(MsgCommon.SCHint.class, SCHint);
		classToId.put(MsgFriend.CSAddFriend.class, CSAddFriend);
		classToId.put(MsgFriend.SCAddFriend.class, SCAddFriend);
		classToId.put(MsgFriend.CSDeleteFriend.class, CSDeleteFriend);
		classToId.put(MsgFriend.SCDeleteFriend.class, SCDeleteFriend);
		classToId.put(MsgFriend.CSLaud.class, CSLaud);
		classToId.put(MsgFriend.SCLaudResult.class, SCLaudResult);
		classToId.put(MsgFriend.CSFindFriend.class, CSFindFriend);
		classToId.put(MsgFriend.SCFindFriend.class, SCFindFriend);
		classToId.put(MsgFriend.CSGetMyFriends.class, CSGetMyFriends);
		classToId.put(MsgFriend.SCGetMyFriends.class, SCGetMyFriends);
		classToId.put(MsgFriend.CSGetMyFans.class, CSGetMyFans);
		classToId.put(MsgFriend.SCGetMyFans.class, SCGetMyFans);
		classToId.put(MsgFriend.CSGetPlayerInfo.class, CSGetPlayerInfo);
		classToId.put(MsgFriend.SCGetPlayerInfo.class, SCGetPlayerInfo);
		classToId.put(MsgFriend.CSGetPlayerLaud.class, CSGetPlayerLaud);
		classToId.put(MsgFriend.SCGetPlayerLaud.class, SCGetPlayerLaud);
		classToId.put(MsgFriend.CSGetRecentPlayer.class, CSGetRecentPlayer);
		classToId.put(MsgFriend.SCGetRecentPlayers.class, SCGetRecentPlayers);
		classToId.put(MsgFriend.CSGetNearbyPlayer.class, CSGetNearbyPlayer);
		classToId.put(MsgFriend.SCGetNearbyPlayer.class, SCGetNearbyPlayer);
		classToId.put(MsgFriend.CSGetBlacklist.class, CSGetBlacklist);
		classToId.put(MsgFriend.SCGetBlacklist.class, SCGetBlacklist);
		classToId.put(MsgFriend.CSAddBlacklist.class, CSAddBlacklist);
		classToId.put(MsgFriend.SCAddBlacklist.class, SCAddBlacklist);
		classToId.put(MsgFriend.CSRemoveBlacklist.class, CSRemoveBlacklist);
		classToId.put(MsgFriend.SCRemoveBlacklist.class, SCRemoveBlacklist);
		classToId.put(MsgFriend.CSGetRecentChat.class, CSGetRecentChat);
		classToId.put(MsgFriend.SCGetRecentChat.class, SCGetRecentChat);
		classToId.put(MsgHumanInfo.CSChangeName.class, CSChangeName);
		classToId.put(MsgHumanInfo.SCChangeName.class, SCChangeName);
		classToId.put(MsgHumanInfo.CSChangeHead.class, CSChangeHead);
		classToId.put(MsgHumanInfo.SCChangeHead.class, SCChangeHead);
		classToId.put(MsgHumanInfo.CSGetHistoryData.class, CSGetHistoryData);
		classToId.put(MsgHumanInfo.SCGetHistoryData.class, SCGetHistoryData);
		classToId.put(MsgHumanInfo.CSGetRoundData.class, CSGetRoundData);
		classToId.put(MsgHumanInfo.SCGetRoundData.class, SCGetRoundData);
		classToId.put(MsgHumanInfo.CSGetSeasonData.class, CSGetSeasonData);
		classToId.put(MsgHumanInfo.SCGetSeasonData.class, SCGetSeasonData);
		classToId.put(MsgHut.SCUpdataHutItem.class, SCUpdataHutItem);
		classToId.put(MsgHut.CSOpenHut.class, CSOpenHut);
		classToId.put(MsgHut.SCOpenHut.class, SCOpenHut);
		classToId.put(MsgHut.CSUseHutItem.class, CSUseHutItem);
		classToId.put(MsgHut.SCUseHutItem.class, SCUseHutItem);
		classToId.put(MsgHut.CSBuyHutItem.class, CSBuyHutItem);
		classToId.put(MsgHut.SCBuyHutItem.class, SCBuyHutItem);
		classToId.put(MsgHut.CSGiftHutItem.class, CSGiftHutItem);
		classToId.put(MsgHut.SCGiftHutItem.class, SCGiftHutItem);
		classToId.put(MsgMail.SCInitMailList.class, SCInitMailList);
		classToId.put(MsgMail.CSGetMailDetail.class, CSGetMailDetail);
		classToId.put(MsgMail.SCGetMailDetail.class, SCGetMailDetail);
		classToId.put(MsgMail.CSReadMail.class, CSReadMail);
		classToId.put(MsgMail.SCReadMail.class, SCReadMail);
		classToId.put(MsgMail.SCMailNewRemind.class, SCMailNewRemind);
		classToId.put(MsgMail.CSPickupMailItem.class, CSPickupMailItem);
		classToId.put(MsgMail.SCPickupItemMailResult.class, SCPickupItemMailResult);
		classToId.put(MsgMail.CSMailRemove.class, CSMailRemove);
		classToId.put(MsgMail.SCMailRemove.class, SCMailRemove);
		classToId.put(MsgRoom.CSRoomCreate.class, CSRoomCreate);
		classToId.put(MsgRoom.SCRoomCreateResult.class, SCRoomCreateResult);
		classToId.put(MsgRoom.CSRoomAddRobot.class, CSRoomAddRobot);
		classToId.put(MsgRoom.SCRoomAddRobot.class, SCRoomAddRobot);
		classToId.put(MsgRoom.CSRoomKick.class, CSRoomKick);
		classToId.put(MsgRoom.SCRoomKick.class, SCRoomKick);
		classToId.put(MsgRoom.CSRoomInvite.class, CSRoomInvite);
		classToId.put(MsgRoom.SCRoomInvite.class, SCRoomInvite);
		classToId.put(MsgRoom.CSRoomChangeIndex.class, CSRoomChangeIndex);
		classToId.put(MsgRoom.SCRoomChangeIndex.class, SCRoomChangeIndex);
		classToId.put(MsgRoom.CSRoomSwapIndexRequest.class, CSRoomSwapIndexRequest);
		classToId.put(MsgRoom.SCRoomSwapIndexRequest.class, SCRoomSwapIndexRequest);
		classToId.put(MsgRoom.CSRoomSwapIndexResponse.class, CSRoomSwapIndexResponse);
		classToId.put(MsgRoom.SCRoomSwapIndexResponse.class, SCRoomSwapIndexResponse);
		classToId.put(MsgTeam.CSTeamCreate.class, CSTeamCreate);
		classToId.put(MsgTeam.SCTeamCreateResult.class, SCTeamCreateResult);
		classToId.put(MsgTeam.CSTeamInvite.class, CSTeamInvite);
		classToId.put(MsgTeam.SCTeamInviteResult.class, SCTeamInviteResult);
		classToId.put(MsgTeam.SCTeamInviteReceive.class, SCTeamInviteReceive);
		classToId.put(MsgTeam.CSTeamJoin.class, CSTeamJoin);
		classToId.put(MsgTeam.SCTeamJoinResult.class, SCTeamJoinResult);
		classToId.put(MsgTeam.SCTeamJoin.class, SCTeamJoin);
		classToId.put(MsgTeam.CSTeamLeave.class, CSTeamLeave);
		classToId.put(MsgTeam.SCTeamLeaveResult.class, SCTeamLeaveResult);
		classToId.put(MsgTeam.SCTeamLeave.class, SCTeamLeave);
		classToId.put(MsgTeam.CSTeamKick.class, CSTeamKick);
		classToId.put(MsgTeam.SCTeamKickResult.class, SCTeamKickResult);
		classToId.put(MsgTeam.SCTeamKick.class, SCTeamKick);
		classToId.put(MsgTeam.CSTeamPrepare.class, CSTeamPrepare);
		classToId.put(MsgTeam.SCTeamPrepareResult.class, SCTeamPrepareResult);
		classToId.put(MsgTeam.SCTeamPrepare.class, SCTeamPrepare);
		classToId.put(MsgTeam.SCSyncMemberInfo.class, SCSyncMemberInfo);
		classToId.put(MsgTest.CSGmCmd.class, CSGmCmd);
	}
	
	/**
	 * 初始化消息ID与消息CLASS的对应关系
	 */
	private static void initIdToClass() {
		idToClass.put(CSLogin,MsgAccount.CSLogin.class);
		idToClass.put(SCLoginResult,MsgAccount.SCLoginResult.class);
		idToClass.put(CSAccountReconnect,MsgAccount.CSAccountReconnect.class);
		idToClass.put(SCAccountReconnectResult,MsgAccount.SCAccountReconnectResult.class);
		idToClass.put(SCAccountLoginQueue,MsgAccount.SCAccountLoginQueue.class);
		idToClass.put(CSKeyActivate,MsgAccount.CSKeyActivate.class);
		idToClass.put(SCKeyActivate,MsgAccount.SCKeyActivate.class);
		idToClass.put(CSBattleStart,MsgBattle.CSBattleStart.class);
		idToClass.put(SCBattleStart,MsgBattle.SCBattleStart.class);
		idToClass.put(CSBattleDo,MsgBattle.CSBattleDo.class);
		idToClass.put(SCBattleDo,MsgBattle.SCBattleDo.class);
		idToClass.put(CSBattleOver,MsgBattle.CSBattleOver.class);
		idToClass.put(SCBattleOver,MsgBattle.SCBattleOver.class);
		idToClass.put(SCTick,MsgBattle.SCTick.class);
		idToClass.put(SCBattleStageSwitch,MsgBattle.SCBattleStageSwitch.class);
		idToClass.put(CSBattleStageSwitchEnd,MsgBattle.CSBattleStageSwitchEnd.class);
		idToClass.put(CSBattleLeave,MsgBattle.CSBattleLeave.class);
		idToClass.put(SCBattleLeaveResult,MsgBattle.SCBattleLeaveResult.class);
		idToClass.put(SCUpdataCard,MsgCard.SCUpdataCard.class);
		idToClass.put(CSCarryCard,MsgCard.CSCarryCard.class);
		idToClass.put(SCCarryCard,MsgCard.SCCarryCard.class);
		idToClass.put(CSTakeOffCard,MsgCard.CSTakeOffCard.class);
		idToClass.put(SCTakeOffCard,MsgCard.SCTakeOffCard.class);
		idToClass.put(CSCardLevelUp,MsgCard.CSCardLevelUp.class);
		idToClass.put(SCCardLevelUp,MsgCard.SCCardLevelUp.class);
		idToClass.put(SCChat,MsgChat.SCChat.class);
		idToClass.put(CSChat,MsgChat.CSChat.class);
		idToClass.put(SCChatPack,MsgChat.SCChatPack.class);
		idToClass.put(CSGetWorldChatBuffer,MsgChat.CSGetWorldChatBuffer.class);
		idToClass.put(CSInitData,MsgCommon.CSInitData.class);
		idToClass.put(SCInitData,MsgCommon.SCInitData.class);
		idToClass.put(SCHumanInfoChange,MsgCommon.SCHumanInfoChange.class);
		idToClass.put(SCServerConfig,MsgCommon.SCServerConfig.class);
		idToClass.put(CSPing,MsgCommon.CSPing.class);
		idToClass.put(SCPing,MsgCommon.SCPing.class);
		idToClass.put(SCHumanKick,MsgCommon.SCHumanKick.class);
		idToClass.put(SCHint,MsgCommon.SCHint.class);
		idToClass.put(CSAddFriend,MsgFriend.CSAddFriend.class);
		idToClass.put(SCAddFriend,MsgFriend.SCAddFriend.class);
		idToClass.put(CSDeleteFriend,MsgFriend.CSDeleteFriend.class);
		idToClass.put(SCDeleteFriend,MsgFriend.SCDeleteFriend.class);
		idToClass.put(CSLaud,MsgFriend.CSLaud.class);
		idToClass.put(SCLaudResult,MsgFriend.SCLaudResult.class);
		idToClass.put(CSFindFriend,MsgFriend.CSFindFriend.class);
		idToClass.put(SCFindFriend,MsgFriend.SCFindFriend.class);
		idToClass.put(CSGetMyFriends,MsgFriend.CSGetMyFriends.class);
		idToClass.put(SCGetMyFriends,MsgFriend.SCGetMyFriends.class);
		idToClass.put(CSGetMyFans,MsgFriend.CSGetMyFans.class);
		idToClass.put(SCGetMyFans,MsgFriend.SCGetMyFans.class);
		idToClass.put(CSGetPlayerInfo,MsgFriend.CSGetPlayerInfo.class);
		idToClass.put(SCGetPlayerInfo,MsgFriend.SCGetPlayerInfo.class);
		idToClass.put(CSGetPlayerLaud,MsgFriend.CSGetPlayerLaud.class);
		idToClass.put(SCGetPlayerLaud,MsgFriend.SCGetPlayerLaud.class);
		idToClass.put(CSGetRecentPlayer,MsgFriend.CSGetRecentPlayer.class);
		idToClass.put(SCGetRecentPlayers,MsgFriend.SCGetRecentPlayers.class);
		idToClass.put(CSGetNearbyPlayer,MsgFriend.CSGetNearbyPlayer.class);
		idToClass.put(SCGetNearbyPlayer,MsgFriend.SCGetNearbyPlayer.class);
		idToClass.put(CSGetBlacklist,MsgFriend.CSGetBlacklist.class);
		idToClass.put(SCGetBlacklist,MsgFriend.SCGetBlacklist.class);
		idToClass.put(CSAddBlacklist,MsgFriend.CSAddBlacklist.class);
		idToClass.put(SCAddBlacklist,MsgFriend.SCAddBlacklist.class);
		idToClass.put(CSRemoveBlacklist,MsgFriend.CSRemoveBlacklist.class);
		idToClass.put(SCRemoveBlacklist,MsgFriend.SCRemoveBlacklist.class);
		idToClass.put(CSGetRecentChat,MsgFriend.CSGetRecentChat.class);
		idToClass.put(SCGetRecentChat,MsgFriend.SCGetRecentChat.class);
		idToClass.put(CSChangeName,MsgHumanInfo.CSChangeName.class);
		idToClass.put(SCChangeName,MsgHumanInfo.SCChangeName.class);
		idToClass.put(CSChangeHead,MsgHumanInfo.CSChangeHead.class);
		idToClass.put(SCChangeHead,MsgHumanInfo.SCChangeHead.class);
		idToClass.put(CSGetHistoryData,MsgHumanInfo.CSGetHistoryData.class);
		idToClass.put(SCGetHistoryData,MsgHumanInfo.SCGetHistoryData.class);
		idToClass.put(CSGetRoundData,MsgHumanInfo.CSGetRoundData.class);
		idToClass.put(SCGetRoundData,MsgHumanInfo.SCGetRoundData.class);
		idToClass.put(CSGetSeasonData,MsgHumanInfo.CSGetSeasonData.class);
		idToClass.put(SCGetSeasonData,MsgHumanInfo.SCGetSeasonData.class);
		idToClass.put(SCUpdataHutItem,MsgHut.SCUpdataHutItem.class);
		idToClass.put(CSOpenHut,MsgHut.CSOpenHut.class);
		idToClass.put(SCOpenHut,MsgHut.SCOpenHut.class);
		idToClass.put(CSUseHutItem,MsgHut.CSUseHutItem.class);
		idToClass.put(SCUseHutItem,MsgHut.SCUseHutItem.class);
		idToClass.put(CSBuyHutItem,MsgHut.CSBuyHutItem.class);
		idToClass.put(SCBuyHutItem,MsgHut.SCBuyHutItem.class);
		idToClass.put(CSGiftHutItem,MsgHut.CSGiftHutItem.class);
		idToClass.put(SCGiftHutItem,MsgHut.SCGiftHutItem.class);
		idToClass.put(SCInitMailList,MsgMail.SCInitMailList.class);
		idToClass.put(CSGetMailDetail,MsgMail.CSGetMailDetail.class);
		idToClass.put(SCGetMailDetail,MsgMail.SCGetMailDetail.class);
		idToClass.put(CSReadMail,MsgMail.CSReadMail.class);
		idToClass.put(SCReadMail,MsgMail.SCReadMail.class);
		idToClass.put(SCMailNewRemind,MsgMail.SCMailNewRemind.class);
		idToClass.put(CSPickupMailItem,MsgMail.CSPickupMailItem.class);
		idToClass.put(SCPickupItemMailResult,MsgMail.SCPickupItemMailResult.class);
		idToClass.put(CSMailRemove,MsgMail.CSMailRemove.class);
		idToClass.put(SCMailRemove,MsgMail.SCMailRemove.class);
		idToClass.put(CSRoomCreate,MsgRoom.CSRoomCreate.class);
		idToClass.put(SCRoomCreateResult,MsgRoom.SCRoomCreateResult.class);
		idToClass.put(CSRoomAddRobot,MsgRoom.CSRoomAddRobot.class);
		idToClass.put(SCRoomAddRobot,MsgRoom.SCRoomAddRobot.class);
		idToClass.put(CSRoomKick,MsgRoom.CSRoomKick.class);
		idToClass.put(SCRoomKick,MsgRoom.SCRoomKick.class);
		idToClass.put(CSRoomInvite,MsgRoom.CSRoomInvite.class);
		idToClass.put(SCRoomInvite,MsgRoom.SCRoomInvite.class);
		idToClass.put(CSRoomChangeIndex,MsgRoom.CSRoomChangeIndex.class);
		idToClass.put(SCRoomChangeIndex,MsgRoom.SCRoomChangeIndex.class);
		idToClass.put(CSRoomSwapIndexRequest,MsgRoom.CSRoomSwapIndexRequest.class);
		idToClass.put(SCRoomSwapIndexRequest,MsgRoom.SCRoomSwapIndexRequest.class);
		idToClass.put(CSRoomSwapIndexResponse,MsgRoom.CSRoomSwapIndexResponse.class);
		idToClass.put(SCRoomSwapIndexResponse,MsgRoom.SCRoomSwapIndexResponse.class);
		idToClass.put(CSTeamCreate,MsgTeam.CSTeamCreate.class);
		idToClass.put(SCTeamCreateResult,MsgTeam.SCTeamCreateResult.class);
		idToClass.put(CSTeamInvite,MsgTeam.CSTeamInvite.class);
		idToClass.put(SCTeamInviteResult,MsgTeam.SCTeamInviteResult.class);
		idToClass.put(SCTeamInviteReceive,MsgTeam.SCTeamInviteReceive.class);
		idToClass.put(CSTeamJoin,MsgTeam.CSTeamJoin.class);
		idToClass.put(SCTeamJoinResult,MsgTeam.SCTeamJoinResult.class);
		idToClass.put(SCTeamJoin,MsgTeam.SCTeamJoin.class);
		idToClass.put(CSTeamLeave,MsgTeam.CSTeamLeave.class);
		idToClass.put(SCTeamLeaveResult,MsgTeam.SCTeamLeaveResult.class);
		idToClass.put(SCTeamLeave,MsgTeam.SCTeamLeave.class);
		idToClass.put(CSTeamKick,MsgTeam.CSTeamKick.class);
		idToClass.put(SCTeamKickResult,MsgTeam.SCTeamKickResult.class);
		idToClass.put(SCTeamKick,MsgTeam.SCTeamKick.class);
		idToClass.put(CSTeamPrepare,MsgTeam.CSTeamPrepare.class);
		idToClass.put(SCTeamPrepareResult,MsgTeam.SCTeamPrepareResult.class);
		idToClass.put(SCTeamPrepare,MsgTeam.SCTeamPrepare.class);
		idToClass.put(SCSyncMemberInfo,MsgTeam.SCSyncMemberInfo.class);
		idToClass.put(CSGmCmd,MsgTest.CSGmCmd.class);
	}
	/**
	 * 根据消息id解析消息
	 */
	public static GeneratedMessage parseFrom(int msgId, CodedInputStream s) throws IOException{
		switch (msgId) {
			case CSLogin:
				return MsgAccount.CSLogin.parseFrom(s);
			case SCLoginResult:
				return MsgAccount.SCLoginResult.parseFrom(s);
			case CSAccountReconnect:
				return MsgAccount.CSAccountReconnect.parseFrom(s);
			case SCAccountReconnectResult:
				return MsgAccount.SCAccountReconnectResult.parseFrom(s);
			case SCAccountLoginQueue:
				return MsgAccount.SCAccountLoginQueue.parseFrom(s);
			case CSKeyActivate:
				return MsgAccount.CSKeyActivate.parseFrom(s);
			case SCKeyActivate:
				return MsgAccount.SCKeyActivate.parseFrom(s);
			case CSBattleStart:
				return MsgBattle.CSBattleStart.parseFrom(s);
			case SCBattleStart:
				return MsgBattle.SCBattleStart.parseFrom(s);
			case CSBattleDo:
				return MsgBattle.CSBattleDo.parseFrom(s);
			case SCBattleDo:
				return MsgBattle.SCBattleDo.parseFrom(s);
			case CSBattleOver:
				return MsgBattle.CSBattleOver.parseFrom(s);
			case SCBattleOver:
				return MsgBattle.SCBattleOver.parseFrom(s);
			case SCTick:
				return MsgBattle.SCTick.parseFrom(s);
			case SCBattleStageSwitch:
				return MsgBattle.SCBattleStageSwitch.parseFrom(s);
			case CSBattleStageSwitchEnd:
				return MsgBattle.CSBattleStageSwitchEnd.parseFrom(s);
			case CSBattleLeave:
				return MsgBattle.CSBattleLeave.parseFrom(s);
			case SCBattleLeaveResult:
				return MsgBattle.SCBattleLeaveResult.parseFrom(s);
			case SCUpdataCard:
				return MsgCard.SCUpdataCard.parseFrom(s);
			case CSCarryCard:
				return MsgCard.CSCarryCard.parseFrom(s);
			case SCCarryCard:
				return MsgCard.SCCarryCard.parseFrom(s);
			case CSTakeOffCard:
				return MsgCard.CSTakeOffCard.parseFrom(s);
			case SCTakeOffCard:
				return MsgCard.SCTakeOffCard.parseFrom(s);
			case CSCardLevelUp:
				return MsgCard.CSCardLevelUp.parseFrom(s);
			case SCCardLevelUp:
				return MsgCard.SCCardLevelUp.parseFrom(s);
			case SCChat:
				return MsgChat.SCChat.parseFrom(s);
			case CSChat:
				return MsgChat.CSChat.parseFrom(s);
			case SCChatPack:
				return MsgChat.SCChatPack.parseFrom(s);
			case CSGetWorldChatBuffer:
				return MsgChat.CSGetWorldChatBuffer.parseFrom(s);
			case CSInitData:
				return MsgCommon.CSInitData.parseFrom(s);
			case SCInitData:
				return MsgCommon.SCInitData.parseFrom(s);
			case SCHumanInfoChange:
				return MsgCommon.SCHumanInfoChange.parseFrom(s);
			case SCServerConfig:
				return MsgCommon.SCServerConfig.parseFrom(s);
			case CSPing:
				return MsgCommon.CSPing.parseFrom(s);
			case SCPing:
				return MsgCommon.SCPing.parseFrom(s);
			case SCHumanKick:
				return MsgCommon.SCHumanKick.parseFrom(s);
			case SCHint:
				return MsgCommon.SCHint.parseFrom(s);
			case CSAddFriend:
				return MsgFriend.CSAddFriend.parseFrom(s);
			case SCAddFriend:
				return MsgFriend.SCAddFriend.parseFrom(s);
			case CSDeleteFriend:
				return MsgFriend.CSDeleteFriend.parseFrom(s);
			case SCDeleteFriend:
				return MsgFriend.SCDeleteFriend.parseFrom(s);
			case CSLaud:
				return MsgFriend.CSLaud.parseFrom(s);
			case SCLaudResult:
				return MsgFriend.SCLaudResult.parseFrom(s);
			case CSFindFriend:
				return MsgFriend.CSFindFriend.parseFrom(s);
			case SCFindFriend:
				return MsgFriend.SCFindFriend.parseFrom(s);
			case CSGetMyFriends:
				return MsgFriend.CSGetMyFriends.parseFrom(s);
			case SCGetMyFriends:
				return MsgFriend.SCGetMyFriends.parseFrom(s);
			case CSGetMyFans:
				return MsgFriend.CSGetMyFans.parseFrom(s);
			case SCGetMyFans:
				return MsgFriend.SCGetMyFans.parseFrom(s);
			case CSGetPlayerInfo:
				return MsgFriend.CSGetPlayerInfo.parseFrom(s);
			case SCGetPlayerInfo:
				return MsgFriend.SCGetPlayerInfo.parseFrom(s);
			case CSGetPlayerLaud:
				return MsgFriend.CSGetPlayerLaud.parseFrom(s);
			case SCGetPlayerLaud:
				return MsgFriend.SCGetPlayerLaud.parseFrom(s);
			case CSGetRecentPlayer:
				return MsgFriend.CSGetRecentPlayer.parseFrom(s);
			case SCGetRecentPlayers:
				return MsgFriend.SCGetRecentPlayers.parseFrom(s);
			case CSGetNearbyPlayer:
				return MsgFriend.CSGetNearbyPlayer.parseFrom(s);
			case SCGetNearbyPlayer:
				return MsgFriend.SCGetNearbyPlayer.parseFrom(s);
			case CSGetBlacklist:
				return MsgFriend.CSGetBlacklist.parseFrom(s);
			case SCGetBlacklist:
				return MsgFriend.SCGetBlacklist.parseFrom(s);
			case CSAddBlacklist:
				return MsgFriend.CSAddBlacklist.parseFrom(s);
			case SCAddBlacklist:
				return MsgFriend.SCAddBlacklist.parseFrom(s);
			case CSRemoveBlacklist:
				return MsgFriend.CSRemoveBlacklist.parseFrom(s);
			case SCRemoveBlacklist:
				return MsgFriend.SCRemoveBlacklist.parseFrom(s);
			case CSGetRecentChat:
				return MsgFriend.CSGetRecentChat.parseFrom(s);
			case SCGetRecentChat:
				return MsgFriend.SCGetRecentChat.parseFrom(s);
			case CSChangeName:
				return MsgHumanInfo.CSChangeName.parseFrom(s);
			case SCChangeName:
				return MsgHumanInfo.SCChangeName.parseFrom(s);
			case CSChangeHead:
				return MsgHumanInfo.CSChangeHead.parseFrom(s);
			case SCChangeHead:
				return MsgHumanInfo.SCChangeHead.parseFrom(s);
			case CSGetHistoryData:
				return MsgHumanInfo.CSGetHistoryData.parseFrom(s);
			case SCGetHistoryData:
				return MsgHumanInfo.SCGetHistoryData.parseFrom(s);
			case CSGetRoundData:
				return MsgHumanInfo.CSGetRoundData.parseFrom(s);
			case SCGetRoundData:
				return MsgHumanInfo.SCGetRoundData.parseFrom(s);
			case CSGetSeasonData:
				return MsgHumanInfo.CSGetSeasonData.parseFrom(s);
			case SCGetSeasonData:
				return MsgHumanInfo.SCGetSeasonData.parseFrom(s);
			case SCUpdataHutItem:
				return MsgHut.SCUpdataHutItem.parseFrom(s);
			case CSOpenHut:
				return MsgHut.CSOpenHut.parseFrom(s);
			case SCOpenHut:
				return MsgHut.SCOpenHut.parseFrom(s);
			case CSUseHutItem:
				return MsgHut.CSUseHutItem.parseFrom(s);
			case SCUseHutItem:
				return MsgHut.SCUseHutItem.parseFrom(s);
			case CSBuyHutItem:
				return MsgHut.CSBuyHutItem.parseFrom(s);
			case SCBuyHutItem:
				return MsgHut.SCBuyHutItem.parseFrom(s);
			case CSGiftHutItem:
				return MsgHut.CSGiftHutItem.parseFrom(s);
			case SCGiftHutItem:
				return MsgHut.SCGiftHutItem.parseFrom(s);
			case SCInitMailList:
				return MsgMail.SCInitMailList.parseFrom(s);
			case CSGetMailDetail:
				return MsgMail.CSGetMailDetail.parseFrom(s);
			case SCGetMailDetail:
				return MsgMail.SCGetMailDetail.parseFrom(s);
			case CSReadMail:
				return MsgMail.CSReadMail.parseFrom(s);
			case SCReadMail:
				return MsgMail.SCReadMail.parseFrom(s);
			case SCMailNewRemind:
				return MsgMail.SCMailNewRemind.parseFrom(s);
			case CSPickupMailItem:
				return MsgMail.CSPickupMailItem.parseFrom(s);
			case SCPickupItemMailResult:
				return MsgMail.SCPickupItemMailResult.parseFrom(s);
			case CSMailRemove:
				return MsgMail.CSMailRemove.parseFrom(s);
			case SCMailRemove:
				return MsgMail.SCMailRemove.parseFrom(s);
			case CSRoomCreate:
				return MsgRoom.CSRoomCreate.parseFrom(s);
			case SCRoomCreateResult:
				return MsgRoom.SCRoomCreateResult.parseFrom(s);
			case CSRoomAddRobot:
				return MsgRoom.CSRoomAddRobot.parseFrom(s);
			case SCRoomAddRobot:
				return MsgRoom.SCRoomAddRobot.parseFrom(s);
			case CSRoomKick:
				return MsgRoom.CSRoomKick.parseFrom(s);
			case SCRoomKick:
				return MsgRoom.SCRoomKick.parseFrom(s);
			case CSRoomInvite:
				return MsgRoom.CSRoomInvite.parseFrom(s);
			case SCRoomInvite:
				return MsgRoom.SCRoomInvite.parseFrom(s);
			case CSRoomChangeIndex:
				return MsgRoom.CSRoomChangeIndex.parseFrom(s);
			case SCRoomChangeIndex:
				return MsgRoom.SCRoomChangeIndex.parseFrom(s);
			case CSRoomSwapIndexRequest:
				return MsgRoom.CSRoomSwapIndexRequest.parseFrom(s);
			case SCRoomSwapIndexRequest:
				return MsgRoom.SCRoomSwapIndexRequest.parseFrom(s);
			case CSRoomSwapIndexResponse:
				return MsgRoom.CSRoomSwapIndexResponse.parseFrom(s);
			case SCRoomSwapIndexResponse:
				return MsgRoom.SCRoomSwapIndexResponse.parseFrom(s);
			case CSTeamCreate:
				return MsgTeam.CSTeamCreate.parseFrom(s);
			case SCTeamCreateResult:
				return MsgTeam.SCTeamCreateResult.parseFrom(s);
			case CSTeamInvite:
				return MsgTeam.CSTeamInvite.parseFrom(s);
			case SCTeamInviteResult:
				return MsgTeam.SCTeamInviteResult.parseFrom(s);
			case SCTeamInviteReceive:
				return MsgTeam.SCTeamInviteReceive.parseFrom(s);
			case CSTeamJoin:
				return MsgTeam.CSTeamJoin.parseFrom(s);
			case SCTeamJoinResult:
				return MsgTeam.SCTeamJoinResult.parseFrom(s);
			case SCTeamJoin:
				return MsgTeam.SCTeamJoin.parseFrom(s);
			case CSTeamLeave:
				return MsgTeam.CSTeamLeave.parseFrom(s);
			case SCTeamLeaveResult:
				return MsgTeam.SCTeamLeaveResult.parseFrom(s);
			case SCTeamLeave:
				return MsgTeam.SCTeamLeave.parseFrom(s);
			case CSTeamKick:
				return MsgTeam.CSTeamKick.parseFrom(s);
			case SCTeamKickResult:
				return MsgTeam.SCTeamKickResult.parseFrom(s);
			case SCTeamKick:
				return MsgTeam.SCTeamKick.parseFrom(s);
			case CSTeamPrepare:
				return MsgTeam.CSTeamPrepare.parseFrom(s);
			case SCTeamPrepareResult:
				return MsgTeam.SCTeamPrepareResult.parseFrom(s);
			case SCTeamPrepare:
				return MsgTeam.SCTeamPrepare.parseFrom(s);
			case SCSyncMemberInfo:
				return MsgTeam.SCSyncMemberInfo.parseFrom(s);
			case CSGmCmd:
				return MsgTest.CSGmCmd.parseFrom(s);
		}
		return null;
	}
}


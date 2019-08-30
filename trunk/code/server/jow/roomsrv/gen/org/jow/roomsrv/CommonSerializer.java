package org.jow.roomsrv;
import java.io.IOException;
import org.jow.core.InputStream;
import org.jow.core.gen.JowGenFile;

@JowGenFile
public final class CommonSerializer {
	public static org.jow.core.interfaces.ISerializable create(int id, InputStream in) throws IOException {
		switch (id) {
			case -1451853192:
				return new org.jow.common.battle.BattleEndInfo();
			case -1883554581:
				return new org.jow.common.battle.BattlePlayerVO();
			case 1959012824:
				return org.jow.common.constant.ErrorResult.create(in);
			case 456271190:
				return new org.jow.common.entity.central.HumanSimpleDB();
			case 270231306:
				return new org.jow.common.entity.core.IdAllot();
			case -1099641308:
				return new org.jow.common.entity.game.ChatDB();
			case 796144605:
				return new org.jow.common.entity.game.HumanDB();
			case -1844683592:
				return new org.jow.common.entity.login.AccountDB();
			case 582352277:
				return new org.jow.common.game.HumanCentralInfo();
			case 1539109660:
				return new org.jow.common.game.HumanInfo();
			case -1624591985:
				return new org.jow.common.game.HumanOnlineInfo();
			case -64471353:
				return new org.jow.core.Call();
			case -2122429687:
				return new org.jow.core.CallPoint();
			case -1322453289:
				return new org.jow.core.CallReturn();
			case -1998394588:
				return new org.jow.core.Chunk();
			case -1986600258:
				return new org.jow.core.Parms();
			case -1394558342:
				return new org.jow.core.Record();
			case 1283600688:
				return new org.jow.core.RecordTransient();
			case 1242027827:
				return new org.jow.core.db.Field();
			case 538639451:
				return new org.jow.core.db.FieldTable();
			case 509726360:
				return new org.jow.core.support.ConnectionStatus();
			case 769143007:
				return new org.jow.core.support.Parms();
			case -1539888512:
				return new org.jow.core.support.TickTimer();
			case 44852340:
				return org.jow.core.support.tuple.FourTuple.create(in);
			case -723224360:
				return org.jow.core.support.tuple.ThreeTuple.create(in);
			case -286473110:
				return org.jow.core.support.tuple.TwoTuple.create(in);
		}
		return null;
	}
	
	public static void init() {
		InputStream.setCreateCommonFunc(CommonSerializer::create);
	}
}


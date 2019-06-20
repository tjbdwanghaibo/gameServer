package org.jow.roomsrv;
import java.io.IOException;
import org.jow.core.InputStream;
import org.jow.core.gen.JowGenFile;

@JowGenFile
public final class CommonSerializer {
	public static org.jow.core.interfaces.ISerilizable create(int id, InputStream in) throws IOException {
		switch (id) {
			case -64471353:
				return new org.jow.core.Call();
			case -2122429687:
				return new org.jow.core.CallPoint();
			case -1322453289:
				return new org.jow.core.CallReturn();
			case -1998394588:
				return new org.jow.core.Chunk();
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
			case 769142629:
				return new org.jow.core.support.Param();
			case -1539888512:
				return new org.jow.core.support.TickTimer();
			case 1054361378:
				return new org.jow.core.support.random.RandomFixUtils();
			case -1496599885:
				return new org.jow.core.support.random.StageRandomUtils();
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


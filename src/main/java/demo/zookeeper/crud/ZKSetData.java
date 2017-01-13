package demo.zookeeper.crud;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import demo.zookeeper.comm.Configuration;
import demo.zookeeper.conn.ZooKeeperConnection;

public class ZKSetData {
	private static ZooKeeper zk;
	private static ZooKeeperConnection conn;

	// Method to update the data in a znode. Similar to getData but without
	// watcher.
	public static void update(String path, byte[] data) throws KeeperException, InterruptedException {
		zk.setData(path, data, zk.exists(path, true).getVersion());
	}

	public static void main(String[] args) throws InterruptedException, KeeperException {
		String path = "/MyFirstZnode";
		byte[] data = "Success".getBytes(); // Assign data which is to be
											// updated.

		try {
			conn = new ZooKeeperConnection();
			zk = conn.connect(Configuration.HOST);
			update(path, data); // Update znode data to the specified path
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

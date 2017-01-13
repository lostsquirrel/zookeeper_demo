package demo.zookeeper.crud;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import demo.zookeeper.comm.Configuration;
import demo.zookeeper.conn.ZooKeeperConnection;

public class ZKCreate {

	private static final Logger log = LoggerFactory.getLogger(ZKCreate.class);

	// create static instance for zookeeper class.
	private static ZooKeeper zk;

	// create static instance for ZooKeeperConnection class.
	private static ZooKeeperConnection conn;

	// Method to create znode in zookeeper ensemble
	public static void create(String path, byte[] data) throws KeeperException, InterruptedException {
		String res = zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		log.debug(res);
	}

	public static void main(String[] args) {

		// znode path
		String path = "/MyFirstZnode"; // Assign path to znode

		// data in byte array
		byte[] data = "My first zookeeper app".getBytes(); // Declare data

		try {
			conn = new ZooKeeperConnection();
			
			zk = conn.connect(Configuration.HOST);
			create(path, data); // Create the data to the specified path
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage()); // Catch error message
		}
	}
}

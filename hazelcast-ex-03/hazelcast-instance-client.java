import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HazelcastPersonExample {

    public static void main(String[] args) {

        // Hazelcast client yapılandırması
        ClientConfig clientConfig = new ClientConfig();
        // Sunucu IP ve portu gerekiyorsa buraya ekleyebilirsin
        // clientConfig.getNetworkConfig().addAddress("127.0.0.1:5701");

        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

        IMap<Integer, Person> map = client.getMap("persons");

        // 10.000 Person nesnesini map içine koy
        for (int i = 0; i < 10000; i++) {
            Person person = new Person("Person_" + i);
            map.put(i, person);
        }

        System.out.println("10.000 Person nesnesi Hazelcast map içine kondu.");

        // İlk 5 nesneyi geri al ve yazdır
        for (int i = 0; i < 5; i++) {
            Person p = map.get(i);
            System.out.println("Key: " + i + ", Person Name: " + p.getName());
        }

        client.shutdown();
    }
}

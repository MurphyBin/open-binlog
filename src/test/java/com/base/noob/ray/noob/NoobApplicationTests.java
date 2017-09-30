package com.base.noob.ray.noob;

import com.base.noob.ray.noob.NoobThread.NoobUpdate;
import com.base.noob.ray.noob.database.dao.NoobConfigDAO;
import com.base.noob.ray.noob.database.dto.NoobConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoobApplicationTests {

	@Autowired
	private NoobConfigDAO noobConfigDAO;
	@Autowired
	private NoobUpdate noobUpdate;



	@Test
	public void add() {
		try {
			NoobConfig noobConfig = new NoobConfig();
			noobConfig.setContent("jsond");
			noobConfig.setNoobApp("fc-loan");
			noobConfig.setNoobTag("testd");
			noobConfig.setNoobGroup("groupds");
			noobConfig.setStatus(1);
			noobConfig.setGmtTime(new Date());
			noobConfig.setModTime(new Date());
			noobConfigDAO.addNoobConfig(noobConfig);
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	@Test
	public void update() {
		try {
			NoobConfig noobConfig = noobConfigDAO.query(2L);
			assert (null != noobConfig);
			noobConfig.setContent(String.valueOf(UUID.randomUUID()));

			noobConfigDAO.updateNoobConfig(noobConfig);
		} catch (Exception e) {
			System.out.print(e);
		}
	}
}

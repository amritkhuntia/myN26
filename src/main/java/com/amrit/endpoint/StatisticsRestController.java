package com.amrit.endpoint;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amrit.entity.Statistic;
import com.amrit.entity.Transaction;
import com.amrit.repository.StatisticsRepository;

@RestController
@RequestMapping(value = "/statistics")
public class StatisticsRestController {

	@Autowired
	private StatisticsRepository statisticRepo;

	@RequestMapping(value = "/v1.0", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity getStatistics() {
		try {
			int TIME_LIMIT=60000;
			long systemTime=Instant.now().toEpochMilli();
			List<Transaction> statList=statisticRepo.findAllBefore(systemTime);
			Statistic statistic=new Statistic();
			
			statistic.setSum(statList.parallelStream().map(e->e.getAmount()).reduce((double) 0, Double::sum));
			statistic.setAvg(statList.parallelStream().map(e->e.getAmount()).mapToDouble(a -> a).average().getAsDouble());
			statistic.setMax(statList.parallelStream().map(e->e.getAmount()).max(Double::compare).get());
			statistic.setMin(statList.parallelStream().map(e->e.getAmount()).min(Double::compare).get());
			statistic.setMax(statList.parallelStream().map(e->e.getAmount()).count());

			
			return new ResponseEntity<Statistic>(statistic,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Statistic>(HttpStatus.NO_CONTENT);
		}
	}
}
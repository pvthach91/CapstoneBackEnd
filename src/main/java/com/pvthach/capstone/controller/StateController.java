package com.pvthach.capstone.controller;

import com.pvthach.capstone.model.State;
import com.pvthach.capstone.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class StateController {

	@Autowired
	StateRepository stateRepository;

	@GetMapping("/api/guest/states")
	public List<State> getStates() {
		List<State> states = stateRepository.findAll();
		Collections.sort(states, new Comparator<State>() {
			@Override
			public int compare(State o1, State o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return states;
	}
}
/**
 * 
 */
package in.parteek;

import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import in.parteek.beans.*;
import in.parteek.dao.Dao;
import in.parteek.utils.PieChart;

/**
 * Created on : 2019-03-15, 11:12:21 a.m.
 *
 * @author Parteek Dheri
 */
@Controller
public class HomeController {
	Dao dao = new Dao();

	@RequestMapping({ "/", "/ParteekDheri" })
	public String goHome() {
		return "index";
	}

//	CastDummyVotes
	@RequestMapping("/GenerateVoters")
	public String generateDummyRecords(Model model) {
		int size = dao.registerMultipleVoter();
		model.addAttribute("messsage", "Sucessfully created " + size + " voters");
		model.addAttribute("tittle", "Success");
		model.addAttribute("alert_class", "success");
		model.addAttribute("loc", "ParteekDheri");
		return "message";
	}

	@RequestMapping("/CastDummyVotes")
	public String castDummyVotes(Model model) {
		int size = dao.castMultipleVote();
		model.addAttribute("messsage", "Sucessfully casted " + size + " votes");
		model.addAttribute("tittle", "Success");
		model.addAttribute("alert_class", "success");
		model.addAttribute("loc", "ParteekDheri");
		return "message";

	}

	@RequestMapping("/ShowVoters")
	public String showVoters(Model model) {

		List<Voter> voters = dao.getRegersteredVoters();
		model.addAttribute("voters", voters);
		return "showVoters";
	}

	@RequestMapping("/ShowStats")
	private String showStats(Model model) {

		ArrayList<HashMap<String, Integer>> data = dao.getStats();
		PieChart partyStats = new PieChart(data.get(0));
		PieChart voteStats = new PieChart(data.get(1));
		PieChart ageStats = new PieChart(data.get(2));
		model.addAttribute("partyStatsJson", partyStats.getChartAsJson());
		model.addAttribute("voteStatsJson", voteStats.getChartAsJson());
		model.addAttribute("ageStatsJson", ageStats.getChartAsJson());
		return "showstats";

	}

	@RequestMapping(value = "/RegisterVoter", method = RequestMethod.GET)
	private String registerVoterGET(Model model, @ModelAttribute Voter voter) {
		model.addAttribute("dest", "/RegisterVoter");
		return "registerVoter";
	}

	@RequestMapping(value = "/RegisterVoter", method = RequestMethod.POST)
	private String registerVoterPOST(Model model, @ModelAttribute Voter voter) {
		try {
			String id = dao.registerVoter(voter);
			model.addAttribute("messsage", "Sucessfully added a voter with SIN " + id);
			model.addAttribute("tittle", "Success");
			model.addAttribute("alert_class", "success");
		} catch (javax.persistence.PersistenceException e) {
			model.addAttribute("messsage", "Provided SIN already exists, Please try another SIN");
			model.addAttribute("tittle", "Error");
			model.addAttribute("alert_class", "danger");
		} catch (Exception e) {
			model.addAttribute("messsage", e.getMessage());
			model.addAttribute("tittle", "Error");
			model.addAttribute("alert_class", "danger");
		}
		return "message";
	}

	@RequestMapping(value = "/CastVote", method = RequestMethod.GET)
	private String castVoteGET(Model model, @ModelAttribute Vote vote) {
		model.addAttribute("vote", vote);
		return "castVote";
	}

	@RequestMapping(value = "/CastVote", method = RequestMethod.POST)
	private String castVotePOST(Model model, @ModelAttribute Vote vote) {

		try {
			dao.castVote(vote.getVoter().getSIN(), vote.getParty().toString());
			model.addAttribute("messsage", "Sucessfully cast vote");
			model.addAttribute("tittle", "Success");
			model.addAttribute("alert_class", "success");
		} catch (Exception e) {
			System.out.println("--> " + e.getMessage());
			model.addAttribute("messsage", e.getMessage());
			model.addAttribute("tittle", "Error");
			model.addAttribute("alert_class", "danger");
		}
		return "message";
	}

	@RequestMapping("/EditVoter/{sin}")
	public String editVoterWithSin(Model model, @PathVariable String sin, @ModelAttribute Voter voter) {

		voter = dao.getVoterBySIN(sin);

		model.addAttribute("voter", voter);
		model.addAttribute("dest", "/EditVoter");
		model.addAttribute("disable", "true");
		return "registerVoter";
	}

	@RequestMapping(value = "/EditVoter", method = RequestMethod.POST)
	public String editVoter(Model model, @ModelAttribute Voter voter) {

		if (voter == null) {
			return "redirect:/ShowVoters";
		}

		try {
			dao.editVoter(voter);
		} catch (Exception e) {
			System.out.println("--> " + e.getMessage());
			model.addAttribute("messsage", e.getMessage());
			model.addAttribute("tittle", "Error");
			model.addAttribute("alert_class", "danger");
			return "message";
		}

		return "redirect:/ShowVoters";
	}

	@RequestMapping("/DeleteVoter/{sin}")
	public String deleteMovie(Model model, @PathVariable String sin) {

		dao.deleteVoter(sin);
		return "redirect:/ShowVoters";
	}
}

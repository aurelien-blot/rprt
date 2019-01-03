package com.cgi.java.FilRouge.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.java.FilRouge.model.Project;
import com.cgi.java.FilRouge.model.ProjectSequence;
import com.cgi.java.FilRouge.repository.ProjectSequenceRepo;
import com.cgi.java.FilRouge.service.base.BaseService;

@Service
public class ProjectSequenceServiceImpl extends BaseService<ProjectSequenceRepo, ProjectSequence>{
	
	
	public int getNextSequence(Project project) {
		
		ProjectSequence sequence = this.repository.findByProject(project);

		if (sequence == null) {
			// Sequence n'existe pas, on doit en créer une
			Calendar cal = Calendar.getInstance();
			cal.setTime(project.getCreationDate());
			int month = cal.get(Calendar.MONTH);
			
			ProjectSequence newSequence = new ProjectSequence(0, month, project);
			
			sequence = this.repository.saveAndFlush(newSequence);
		}
		
		else {
			// La séquence existe, il faut vérfier si on doit la remettre à 0 en fonction du mois
			
			Calendar now = Calendar.getInstance();
			int currentMonth = now.get(Calendar.MONTH) + 1;
			
			if(sequence.getMonth() == currentMonth) {
				// Le mois de la dernière séquence est égal au mois actuel, on incrémente la séquence
				sequence.setSequence(sequence.getSequence()+1);
			}
			
			else {
				// Le mois de la dernière séquence est différent du mois actuel, on change le mois et on remet à 0 la séquence
				sequence.setMonth(currentMonth);
				sequence.setSequence(0);
			}
			sequence = this.repository.saveAndFlush(sequence);
		}
		
		return sequence.getSequence();
		
	}
	
}

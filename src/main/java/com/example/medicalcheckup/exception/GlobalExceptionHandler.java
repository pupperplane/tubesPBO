package com.example.medicalcheckup.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.thymeleaf.exceptions.TemplateInputException;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFound(EntityNotFoundException e, Model model) {
        model.addAttribute("errorMessage", "Entity not found: " + e.getMessage());
        return new ModelAndView("exception/errorPage", "model", model);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleInvalidArgument(IllegalArgumentException e, Model model) {
        model.addAttribute("errorMessage", "Invalid input: " + e.getMessage());
        return new ModelAndView("exception/errorPage", "model", model);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNoHandlerFound(NoHandlerFoundException e, Model model) {
        model.addAttribute("errorMessage", "The requested page was not found.");
        return new ModelAndView("exception/errorPage", "model", model);
    }

    @ExceptionHandler(TemplateInputException.class)
    public ModelAndView handleTemplateInputException(TemplateInputException e, Model model) {
        model.addAttribute("errorMessage", "Template error: " + e.getMessage());
        return new ModelAndView("exception/errorPage", "model", model);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception e, Model model) {
        model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
        return new ModelAndView("exception/errorPage", "model", model);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDeniedException(AccessDeniedException e, Model model) {
        model.addAttribute("errorMessage", "You do not have permission to access this page.");
        return new ModelAndView("exception/errorPage", "model", model);
    }
}

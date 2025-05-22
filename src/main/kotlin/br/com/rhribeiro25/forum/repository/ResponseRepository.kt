package br.com.rhribeiro25.forum.repository

import org.springframework.data.jpa.repository.JpaRepository

interface ResponseRepository : JpaRepository<br.com.rhribeiro25.forum.model.Response, Long>
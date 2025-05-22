package br.com.rhribeiro25.forum.repository

import br.com.rhribeiro25.forum.model.Response
import org.springframework.data.jpa.repository.JpaRepository

interface ResponseRepository : JpaRepository<Response, Long>
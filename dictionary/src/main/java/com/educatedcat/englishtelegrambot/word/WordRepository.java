package com.educatedcat.englishtelegrambot.word;

import org.springframework.data.repository.*;

import java.util.*;

interface WordRepository extends CrudRepository<Word, UUID> {
}
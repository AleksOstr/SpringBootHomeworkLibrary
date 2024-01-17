package ru.geekbrains.springboothomework3.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.springboothomework3.api.request.ReaderRequest;
import ru.geekbrains.springboothomework3.model.Reader;
import ru.geekbrains.springboothomework3.repository.ReaderRepository;

import java.util.NoSuchElementException;

@Service
public class ReaderService {
    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository repository) {
        this.readerRepository = repository;
    }

    public Reader getReaderById(long id) {
        Reader reader = readerRepository.getReaderById(id);
        if (reader == null) {
            throw new NoSuchElementException();
        }
        return reader;
    }

    public Reader insertReader(ReaderRequest request) {
        if (request.getName() == null) {
            throw new NullPointerException();
        }
        Reader reader = new Reader(request.getName());
        readerRepository.insertReader(reader);
        return reader;
    }

    public void deleteReader(long id) {
        if (readerRepository.getReaderById(id) == null) {
            throw new NoSuchElementException();
        }
        readerRepository.deleteReader(id);
    }
}
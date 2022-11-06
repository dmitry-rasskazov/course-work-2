package rasskazov.laba.springwebservice.Service;

import org.springframework.stereotype.Service;
import rasskazov.laba.springwebservice.Entity.Log;
import rasskazov.laba.springwebservice.Repository.LogRepository;

@Service
public class LogServiceImpl implements LogService
{
    private final LogRepository logRepository;

    public LogServiceImpl(LogRepository logRepository)
    {
        this.logRepository = logRepository;
    }

    @Override
    public void notice(String message)
    {
        logRepository.save(this.createLog(message, TypeMessage.NOTICE));
    }

    @Override
    public void info(String message) {
        logRepository.save(this.createLog(message, TypeMessage.INFO));
    }

    @Override
    public void warn(String message) {
        logRepository.save(this.createLog(message, TypeMessage.WARN));
    }

    private static Long ts()
    {
        return System.currentTimeMillis() / 1000;
    }

    private Log createLog(
            String message,
            TypeMessage type
    ) {
        Log log = new Log();
        log.setMessage(message);
        log.setTs(ts());
        log.setType(type.name());
        return log;
    }

    enum TypeMessage {
        NOTICE,
        INFO,
        WARN
    }
}

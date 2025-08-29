package com.example.base.controller;

import com.example.base.util.result.ResultVo;
import com.example.base.util.threadPool.MyThreadPool;
import com.example.base.util.threadPool.ResizableCapacityLinkedBlockingQueue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Api(tags = "自定义线程池参数接口")
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/Thread/Customized")
public class MyCustomizedController {


    ThreadPoolExecutor cachedThreadPool = MyThreadPool.getInstance();

    @ApiOperation(value = "修改核心线程数", notes = "")
    @GetMapping("/corePoolSize")
    public ResultVo setCorePoolSize(@RequestParam("corePoolSize") Integer corePoolSize) {
        if(cachedThreadPool.getMaximumPoolSize()<corePoolSize){
            return ResultVo.success("设置失败，目标核心线程数大于前最大线程数，请同时设置两者的值或重新设置值");
        }
        cachedThreadPool.setCorePoolSize(corePoolSize);
        return ResultVo.success("设置成功");
    }

    @ApiOperation(value = "修改最大线程数", notes = "")
    @GetMapping("/maximumPoolSize")
    public ResultVo setMaximumPoolSize(@RequestParam("maximumPoolSize") Integer maximumPoolSize) {
        cachedThreadPool.setMaximumPoolSize(maximumPoolSize);
        return ResultVo.success("设置成功");
    }

    @ApiOperation(value = "修改核心线程数和最大线程数", notes = "")
    @GetMapping("/coreAndMax")
    public ResultVo setcoreAndMax(@RequestParam("corePoolSize") Integer corePoolSize,@RequestParam("maximumPoolSize") Integer maximumPoolSize) {
        if(corePoolSize>maximumPoolSize){
            return ResultVo.success("设置失败，核心线程数不应大于最大线程数");
        }
        cachedThreadPool.setCorePoolSize(corePoolSize);
        cachedThreadPool.setMaximumPoolSize(maximumPoolSize);
        return ResultVo.success("设置成功");
    }

    @ApiOperation(value = "修改队列长度", notes = "")
    @GetMapping("/capacity")
    public ResultVo setCapacity(@RequestParam("capacity") Integer capacity) {
        ResizableCapacityLinkedBlockingQueue queue = (ResizableCapacityLinkedBlockingQueue) cachedThreadPool.getQueue();
        queue.setCapacity(capacity);
        return ResultVo.success("设置成功");
    }

    @ApiOperation(value = "线程池情况",notes = "")
    @GetMapping("/poolStatus")
    public ResultVo getPoolStatus(){
        log.info("查看当前线程池状态");
        String str = getStatus();
        return ResultVo.success("线程池情况："+str);
    }

    private String getStatus() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ResizableCapacityLinkedBlockingQueue queue = (ResizableCapacityLinkedBlockingQueue) cachedThreadPool.getQueue();
        String str =sdf.format(new Date())+Thread.currentThread().getName() + "-" +
                "核心线程数：" + cachedThreadPool.getCorePoolSize() +
                "，活动线程数" + cachedThreadPool.getActiveCount() +
                "，最大线程数" + cachedThreadPool.getMaximumPoolSize() +
                "，线程池活跃度" + divide(cachedThreadPool.getActiveCount(), cachedThreadPool.getMaximumPoolSize()) +
                "，任务完成数" + cachedThreadPool.getCompletedTaskCount() +
                "，队列大小" + (queue.size() + queue.remainingCapacity()) +
                "，当前排队线程数" + queue.size() +
                "，队列剩余大小" + queue.remainingCapacity() +
                "，队列使用度" + divide(queue.size(), queue.size() + queue.remainingCapacity());
        log.info(str);
        return str;
    }

    /**
     * 保留两位小数
     * @param num1
     * @param num2
     * @return
     */
    private static String divide(int num1, int num2) {
        return String.format("%1.2f%%", Double.parseDouble(num1 + "") / Double.parseDouble(num2 + "") * 100);
    }
}

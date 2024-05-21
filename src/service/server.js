// const ExcelJS = require('exceljs');
import ExcelJS from 'exceljs'

function Export (req) {
    const scheduleData = req;

    const workbook = new ExcelJS.Workbook();
    const worksheet = workbook.addWorksheet('Teacher Schedule');

    worksheet.columns = [
        { header: '序号', key: 'id', width: 10 },
        { header: '余量/容量', key: 'capacity', width: 15 },
        { header: '课程代码', key: 'courseCode', width: 15 },
        { header: '课程名称', key: 'courseName', width: 25 },
        { header: '教师', key: 'teacher', width: 15 },
        { header: '上课时间', key: 'classTime', width: 25 },
        { header: '上课地点', key: 'classLocation', width: 25 },
    ];

    scheduleData.forEach((item, index) => {
        worksheet.addRow({
            id: index + 1,
            capacity: item.capacity,
            courseCode: item.courseCode,
            courseName: item.courseName,
            teacher: item.teacher,
            classTime: item.classTime,
            classLocation: item.classLocation,
        });
    });

    return workbook.xlsx.writeBuffer();
}

export {Export};
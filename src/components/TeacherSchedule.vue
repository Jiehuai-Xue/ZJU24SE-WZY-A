<template>
  <div class="teacher-schedule" id="print-item">
    <table>
      <thead>
      <tr>
        <th>序号</th>
        <th>余量/容量</th>
        <th>课程代码</th>
        <th>课程名称</th>
        <th>教师</th>
        <th>上课时间</th>
        <th>上课地点</th>
        <th>导出</th>
        <th>打印</th>
        <th>申请</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td>1</td>
        <td>2/60</td>
        <td>21120221</td>
        <td>人工智能</td>
        <td>吴飞</td>
        <td>
          周一 第6-8节(春1-8周; 夏1-7周)<br>
          周一 第11-12节(春,夏1,3,5,7周)
        </td>
        <td>
          玉泉曹光彪大楼西楼-101<br>
          玉泉曹光彪西-503
        </td>
        <td><button @click="exportData">导出</button></td>
        <td><button @click="printData">打印</button></td>
        <td><button @click="applyData">申请</button></td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import * as server from '../service/server.js';
import html2pdf from 'html2pdf.js';

console.log('fuck');
export default {
  name: 'TeacherSchedule',
  methods: {
    exportData() {
      const scheduleData = [
        {
          id: 1,
          capacity: '2/60',
          courseCode: '21120221',
          courseName: '人工智能',
          teacher: '吴飞',
          classTime: '周一 第6-8节(春1-8周; 夏1-7周)\n周一 第11-12节(春,夏1,3,5,7周)',
          classLocation: '玉泉曹光彪大楼西楼-101\n玉泉曹光彪西-503'
        }
      ];

      server.Export(scheduleData).then(
          buffer => {
            const blob = new Blob([buffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
            const url = URL.createObjectURL(blob);

            const a = document.createElement('a');
            a.href = url;
            a.download = 'TeacherSchedule.xlsx';
            document.body.appendChild(a);
            a.click();

            URL.revokeObjectURL(url);
          }
      )
    },
    printData() {
      const element = document.getElementById('print-item');
      html2pdf(element);
      //   const scheduleData = [
      //     {
      //       id: 1,
      //       capacity: '2/60',
      //       courseCode: '21120221',
      //       courseName: '人工智能',
      //       teacher: '吴飞',
      //       classTime: '周一 第6-8节(春1-8周; 夏1-7周)\n周一 第11-12节(春,夏1,3,5,7周)',
      //       classLocation: '玉泉曹光彪大楼西楼-101\n玉泉曹光彪西-503'
      //     }
      //   ];
      //
      //   const buffer = server.Print(scheduleData);
      //   const blob = new Blob([buffer], { type: 'application/pdf' });
      //   const url = URL.createObjectURL(blob);
      //   const a = document.createElement('a');
      //   a.href = url;
      //   a.download = 'TeacherSchedule.xlsx';
      //   document.body.appendChild(a);
      //   a.click();
      //   URL.revokeObjectURL(url);
      // },
      // applyData() {
      //   console.log('Applying...');
      // }
    }
  }
}
</script>

<style scoped>
.teacher-schedule {
  width: 100%;
  border-collapse: collapse;
  font-size: 14px;
}

.header {
  background-color: white;
  color: black;
  padding: 10px 20px;

  display: flex;
  align-items: center;
}

.teacher-info {
  margin: 0;
}

.teacher-info p {
  margin: 2px 0; /* Reduce the space between the paragraphs */
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center;
}

th {
  background-color: #f2f2f2;
}

button {
  background-color: #007BFF;
  color: white;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>

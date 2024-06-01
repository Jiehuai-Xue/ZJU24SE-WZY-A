<template>
  <MDBContainer style="padding: 2rem;">
    <NavBar title='查询课表' />
    <button @click="getPdf()" style="position: absolute; top: 240px; right: 110px;">打印课表</button>
    <div id="pdfDom">
      <Timetable />
    </div>
  </MDBContainer>
</template>

<script setup>
import AppHeader from '../components/AppHeader.vue'
import Timetable from '../components/TimeTable.vue'
import FlexBox from '../components/FlexBox.vue'
import ButtonGroup from '../components/ButtonGroup.vue'
import Dropdowns from '../components/Dropdowns.vue'
import NavBar from "../components/NavBar.vue";
import { MDBContainer, MDBCol, MDBRow, MDBCardGroup, MDBCard, MDBCardBody, MDBCardTitle, MDBCardText, MDBCardImg, MDBBtn } from "mdb-vue-ui-kit";
import html2canvas from 'html2canvas';
import { jsPDF } from "jspdf";
const getPdf = async () => {
  const pdfDom = document.getElementById('pdfDom');
  try {
    const canvas = await html2canvas(pdfDom, {
      logging: true,
      letterRendering: 1,
      useCORS: true
    });
    const imgData = canvas.toDataURL('image/jpeg', 1.0);

    // 创建PDF对象
    const pdf = new jsPDF({
      orientation: 'p',
      unit: 'px',
      format: 'a4'
    });

    // 计算PDF的宽度和高度比例以适配页面
    var imgWidth = 310; // A4 paper size in mm for width
    var pageHeight = 295;  // A4 paper size in mm for height
    var imgHeight = canvas.height * imgWidth / canvas.width;
    var heightLeft = imgHeight;

    // 将图像添加到PDF
    var position = 0;
    pdf.addImage(imgData, 'JPEG', 0, position, imgWidth, imgHeight);
    heightLeft -= pageHeight;

    while (heightLeft >= 0) {
      position = heightLeft - imgHeight;
      pdf.addPage();
      pdf.addImage(imgData, 'JPEG', 0, position, imgWidth, imgHeight);
      heightLeft -= pageHeight;
    }

    // 保存PDF
    pdf.save('学生课表.pdf');
  } catch (e) {
    console.error('导出PDF发生错误：', e);
  }
};
</script>

<style scoped>
#app {
  font-family: Roboto, Helvetica, Arial, sans-serif;
}
</style>

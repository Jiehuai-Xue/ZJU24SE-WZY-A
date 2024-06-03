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
  // 获取页面元素
  const pdfDom = document.getElementById('pdfDom');

  // 确保内容加载完成后再执行
  setTimeout(async () => {
    try {
      const canvas = await html2canvas(pdfDom, {
        logging: true,
        scale: 2, // 提高图片质量
        useCORS: true
      });
      const imgData = canvas.toDataURL('image/jpeg');
      const pdf = new jsPDF();

      const imgProps= pdf.getImageProperties(imgData);
      let pdfWidth = pdf.internal.pageSize.getWidth();
      let pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;

      // 适应PDF单页面
      if (pdfHeight > pdf.internal.pageSize.getHeight()) {
        pdfHeight = pdf.internal.pageSize.getHeight();
        pdfWidth = (imgProps.width * pdfHeight) / imgProps.height;
      }

      pdf.addImage(imgData, 'JPEG', 0, 0, pdfWidth, pdfHeight);
      pdf.save('课表.pdf');
    } catch (e) {
      console.error('导出PDF发生错误：', e);
    }
  }, 1000); // 延迟1秒后执行，可根据实际加载情况调整
};
</script>

<style scoped>
#app {
  font-family: Roboto, Helvetica, Arial, sans-serif;
}
</style>

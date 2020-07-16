//////////////////////////////////////////////////////////////////////////////////	 
//嵌入式开发网
//mcudev.taobao.com
//  功能描述   : OLED 4接口演示例程(STMSTM8S103K3系列)
//              说明: 
//              ----------------------------------------------------------------
//              GND    电源地
//              VCC  接5V或3.3v电源
//              D0   PC1
//              D1   PC2
//              RES  PC3
//              DC   PC4
//              CS   PC5              
//              ----------------------------------------------------------------
 
//******************************************************************************/。

#ifndef __OLED_H
#define __OLED_H			  	 
#include "stm32f1xx_hal.h"
#include "gpio.h"
#define OLED_CMD  0	//写命令
#define OLED_DATA 1	//写数据
#define OLED_MODE 0

/****************时钟*********************/
#define OLED_SCLK_PORT  (GPIOB)
#define OLED_SCLK_PINS  (GPIO_PIN_13)

/****************数据*********************/
#define OLED_SDIN_PORT  (GPIOB)
#define OLED_SDIN_PINS  (GPIO_PIN_15)

/****************复位*********************/
#define OLED_RST_PORT  (GPIOA)
#define OLED_RST_PINS  (GPIO_PIN_8)

/****************数据/命令*********************/
#define OLED_DC_PORT  (GPIOB)
#define OLED_DC_PINS  (GPIO_PIN_14)

/****************片选*********************/
#define OLED_CS_PORT  (GPIOB)
#define OLED_CS_PINS  (GPIO_PIN_12)


#define OLED_SCLK_Clr() HAL_GPIO_WritePin(OLED_SCLK_PORT,OLED_SCLK_PINS,GPIO_PIN_RESET)
#define OLED_SCLK_Set() HAL_GPIO_WritePin(OLED_SCLK_PORT,OLED_SCLK_PINS,GPIO_PIN_SET)

#define OLED_SDIN_Clr() HAL_GPIO_WritePin(OLED_SDIN_PORT,OLED_SDIN_PINS,GPIO_PIN_RESET)
#define OLED_SDIN_Set() HAL_GPIO_WritePin(OLED_SDIN_PORT,OLED_SDIN_PINS,GPIO_PIN_SET)

#define OLED_RST_Clr() HAL_GPIO_WritePin(OLED_RST_PORT,OLED_RST_PINS,GPIO_PIN_RESET)
#define OLED_RST_Set() HAL_GPIO_WritePin(OLED_RST_PORT,OLED_RST_PINS,GPIO_PIN_SET)


#define OLED_DC_Clr() HAL_GPIO_WritePin(OLED_DC_PORT,OLED_DC_PINS,GPIO_PIN_RESET)
#define OLED_DC_Set() HAL_GPIO_WritePin(OLED_DC_PORT,OLED_DC_PINS,GPIO_PIN_SET)


#define OLED_CS_Clr() HAL_GPIO_WritePin(OLED_CS_PORT,OLED_CS_PINS,GPIO_PIN_RESET)
#define OLED_CS_Set() HAL_GPIO_WritePin(OLED_CS_PORT,OLED_CS_PINS,GPIO_PIN_SET)


//OLED模式设置
//0:4线串行模式
//1:并行8080模式

#define SIZE 16
#define XLevelL		0x02
#define XLevelH		0x10
#define Max_Column	128
#define Max_Row		64
#define	Brightness	0xFF 
#define X_WIDTH 	128
#define Y_WIDTH 	64	    						  
//-----------------OLED端口定义----------------  					   

void delay_ms(unsigned int ms);
		     

//OLED控制用函数
void OLED_WR_Byte(uint8_t dat,uint8_t cmd);	    
void OLED_Display_On(void);
void OLED_Display_Off(void);	   							   		    
void OLED_Init(void);
void OLED_Clear(void);
void OLED_DrawPoint(uint8_t x,uint8_t y,uint8_t t);
void OLED_Fill(uint8_t x1,uint8_t y1,uint8_t x2,uint8_t y2,uint8_t dot);
void OLED_ShowChar(uint8_t x,uint8_t y,uint8_t chr);
void OLED_ShowNum(uint8_t x,uint8_t y,uint32_t num,uint8_t len,uint8_t size2);
void OLED_ShowString(uint8_t x,uint8_t y, uint8_t *p);	 
void OLED_Set_Pos(unsigned char x, unsigned char y);
void OLED_ShowCHinese(uint8_t x,uint8_t y,uint8_t no);
void OLED_DrawBMP(unsigned char x0, unsigned char y0,unsigned char x1, unsigned char y1,unsigned char BMP[]);
#endif  
	 




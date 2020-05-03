import groovy.json.JsonOutput
import groovy.transform.Field
import groovy.json.JsonSlurper


node ('Build-Server')
{
    parameters
    {
        choice(choices: 'develop\nrelease\nQA\nmaster' ,
        description:'select the Branch Name' ,
        name:'Branch_Name')
    }

    if ("${Branch_Name}" == 'master')
        {
            Checkout()
            Build()
            Create_Image()
            Image_Push()
	    Deploytok8s()
        }
   

    else
        {
            quote()
        }
}

def Checkout()
{
    stage('Checkout')
    {
        checkout scm
    }
}


def Junit() 
{
    stage('Junit') 
    {
        sh "cd ${params.Service_Name} ; mvn  test"
    }
}

def Build() 
{
    stage('Build') 
    {
       //sh 'cd authutil; mvn clean install'
       sh "cd service-70900 ; mvn clean package -Dmaven.test.skip=true" 
    }
}
def Create_Image()
{
    stage('Create_Image')
     
    {
        sh "sudo docker login -u ankit1111 -pmiet@12345 "
        sh "cd $WORKSPACE/service-70900 ; sudo docker build --tag=service-70900 ."
    }
}

def Image_Push() 
{
    stage('Image_Push')
    {
   
            sh "sudo docker login -u ankit1111 -p miet@12345 "
            sh "sudo docker tag  service-70900 ankit1111/service-70900"
            sh "sudo docker push ankit1111/service-70900:latest"
        }
    }


def Deploytok8s()
{
    stage('DeploytoProd')
    {
      node ("k8-master")
      {
            sh "cd k8s; kubectl create -f  msjava.yaml "
      }

    }
}


